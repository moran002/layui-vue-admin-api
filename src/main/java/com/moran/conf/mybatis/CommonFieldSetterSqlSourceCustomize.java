package com.moran.conf.mybatis;

import com.moran.util.ServletUtil;
import io.mybatis.provider.EntityTable;
import io.mybatis.provider.SqlSourceCustomize;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author : moran
 */
@Slf4j
public class CommonFieldSetterSqlSourceCustomize implements SqlSourceCustomize {

    private static final String CREATE_BY = "createBy";
    private static final String UPDATE_BY = "updateBy";

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";


    @Override
    public SqlSource customize(SqlSource sqlSource, EntityTable entity, MappedStatement ms, ProviderContext context) {
        // 自定义SqlSource实现类，使用lambda简写
        return parameterObject -> {
            // 通过parameterObject、MappedStatement两个对象即可实现全局设置通用字段
            SqlCommandType sqlCommandType = ms.getSqlCommandType();
            if (sqlCommandType == SqlCommandType.INSERT || sqlCommandType == SqlCommandType.UPDATE) {
                MetaObject metaObject = ms.getConfiguration().newMetaObject(parameterObject);
                // AbstractModel是我项目的数据库实体的父类，内部含有createBy、createTime字段
                // TODO: 自行修改此处逻辑，如判断字段是否存在
                if (sqlCommandType == SqlCommandType.INSERT) {
                    setCommonField(metaObject, CREATE_BY, ServletUtil.getUserId());
                    setCommonField(metaObject, CREATE_TIME, LocalDateTime.now());
                    setCommonField(metaObject, UPDATE_BY, ServletUtil.getUserId());
                    setCommonField(metaObject, UPDATE_TIME, LocalDateTime.now());
                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    setCommonField(metaObject, UPDATE_BY, ServletUtil.getUserId());
                    setCommonField(metaObject, UPDATE_TIME, LocalDateTime.now());
                }

            }
            return sqlSource.getBoundSql(parameterObject);
        };
    }

    private void setCommonField(MetaObject metaObject, String field, Object value) {
        Object createBy = metaObject.getValue(field);
        if (createBy == null) {
            metaObject.setValue(field, value);
        }
    }
}
