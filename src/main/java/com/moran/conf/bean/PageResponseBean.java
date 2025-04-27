package com.moran.conf.bean;

import com.github.pagehelper.Page;
import com.moran.conf.constant.CodeConstant;
import com.moran.conf.constant.CommonConstant;
import lombok.Getter;
import lombok.Setter;

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
    private T data;
    /**
     * 总数
     */
    private String total;

    public static <T> PageResponseBean<T> ok() {
        return createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, null);
    }

    public static <T> PageResponseBean<T> ok(T data) {
        PageResponseBean<T> result = createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, data);
        if (data instanceof Page) {
            result.setTotal(String.valueOf(new PageData((Page) data).getTotal()));
        }
        return result;
    }

    public static <T> PageResponseBean<T> ok(Object pageData, T data) {
        PageResponseBean<T> result = createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, data);
        if (pageData instanceof Page) {
            result.setTotal(String.valueOf(new PageData((Page) pageData).getTotal()));
        }
        result.setData(data);
        return result;
    }

    public static <T> PageResponseBean<T> fail(Integer code, String msg) {
        return createResult(code, msg, null);
    }

    public static <T> PageResponseBean<T> fail(String msg) {
        return createResult(CodeConstant.ERROR, msg, null);
    }

    public static <T> PageResponseBean<T> createResult(int code, String msg, T data) {
        PageResponseBean<T> result = new PageResponseBean<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * com.github.pagehelper.Page 的代理类
     */
    static class PageData {
        private Page page;

        PageData(Page page) {
            if (page == null) {
                throw new RuntimeException("page can not be null");
            }
            this.page = page;
        }

        /**
         * 总数
         */
        public long getTotal() {
            return page.getTotal();
        }
    }
}
