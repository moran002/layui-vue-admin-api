package com.moran.conf.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.constant.CodeConstant;
import com.moran.conf.constant.CommonConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : moran
 */
@Setter
@Getter
public class PageResponseBean<T> {
    /**
     * 码值
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private List<T> data;
    /**
     * 总数
     */
    private long total;

    public static <T> PageResponseBean<T> ok() {
        return createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, null);
    }

    public static <T> PageResponseBean<T> ok(Page<T> page) {
        PageResponseBean<T> result = createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, page.getRecords());
        result.setTotal(page.getTotal());
        return result;
    }

    public static <T> PageResponseBean<T> ok(Long total, List<T> data) {
        PageResponseBean<T> result = createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, data);
        result.setTotal(total);
        result.setData(data);
        return result;
    }

    public static <T> PageResponseBean<T> fail(Integer code, String msg) {
        return createResult(code, msg, null);
    }

    public static <T> PageResponseBean<T> fail(String msg) {
        return createResult(CodeConstant.ERROR, msg, null);
    }

    public static <T> PageResponseBean<T> createResult(int code, String msg, List<T> data) {
        PageResponseBean<T> result = new PageResponseBean<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
