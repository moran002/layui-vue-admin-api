package com.moran.conf.mybatis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.bean.SortingField;
import jakarta.servlet.http.HttpServletRequest;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * MyBatis 工具类
 */
public class MyBatisUtils {

    private static final String MYSQL_ESCAPE_CHARACTER = "`";

    public static <T> Page<T> buildPage() {
        return buildPage(null);
    }

    public static <T> Page<T> buildPage(Collection<SortingField> sortingFields) {
        int pageNo = StringUtils.hasLength(Objects.requireNonNull(getRequest()).getParameter("pageNo"))? Integer.parseInt(getRequest().getParameter("pageNo")) : 1;
        int pageSize = StringUtils.hasLength(getRequest().getParameter("pageSize"))? Integer.parseInt(getRequest().getParameter("pageSize")) : 10;
        // 页码 + 数量
        Page<T> page = new Page<>(pageNo, pageSize);
        // 排序字段
        if (!CollectionUtil.isEmpty(sortingFields)) {
            page.addOrder(sortingFields.stream().map(sortingField -> SortingField.ORDER_ASC.equals(sortingField.getOrder())
                            ? OrderItem.asc(StrUtil.toUnderlineCase(sortingField.getField()))
                            : OrderItem.desc(StrUtil.toUnderlineCase(sortingField.getField())))
                    .collect(Collectors.toList()));
        }
        return page;
    }

    /**
     * 将拦截器添加到链中
     * 由于 MybatisPlusInterceptor 不支持添加拦截器，所以只能全量设置
     *
     * @param interceptor 链
     * @param inner       拦截器
     * @param index       位置
     */
    public static void addInterceptor(MybatisPlusInterceptor interceptor, InnerInterceptor inner, int index) {
        List<InnerInterceptor> inners = new ArrayList<>(interceptor.getInterceptors());
        inners.add(index, inner);
        interceptor.setInterceptors(inners);
    }

    /**
     * 获得 Table 对应的表名
     * <p>
     * 兼容 MySQL 转义表名 `t_xxx`
     *
     * @param table 表
     * @return 去除转移字符后的表名
     */
    public static String getTableName(Table table) {
        String tableName = table.getName();
        if (tableName.startsWith(MYSQL_ESCAPE_CHARACTER) && tableName.endsWith(MYSQL_ESCAPE_CHARACTER)) {
            tableName = tableName.substring(1, tableName.length() - 1);
        }
        return tableName;
    }

    /**
     * 构建 Column 对象
     *
     * @param tableName  表名
     * @param tableAlias 别名
     * @param column     字段名
     * @return Column 对象
     */
    public static Column buildColumn(String tableName, Alias tableAlias, String column) {
        if (tableAlias != null) {
            tableName = tableAlias.getName();
        }
        return new Column(tableName + StringPool.DOT + column);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        try
        {
            return Objects.requireNonNull(getRequestAttributes()).getRequest();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        try
        {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
