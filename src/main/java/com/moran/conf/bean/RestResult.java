package com.moran.conf.bean;

import com.github.pagehelper.Page;
import com.moran.conf.constant.CodeConstant;
import com.moran.conf.constant.CommonConstant;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * 操作消息提醒
 *
 * @author moran
 */
public class RestResult extends HashMap<String, Object> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 分页对象
     */
    public static final String TOTAL_TAG = "total";

    /**
     * 初始化一个新创建的 RestResult 对象，使其表示一个空消息。
     */
    public RestResult() {
    }

    /**
     * 初始化一个新创建的 RestResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public RestResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 RestResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public RestResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (Objects.nonNull(data)) {
            super.put(DATA_TAG, data);
            if (data instanceof Page) {
                super.put(TOTAL_TAG, new PageData((Page) data).getTotal());
            }
        }
    }


    /**
     * 其他结果
     *
     * @param code     代码
     * @param msg      信息
     * @param pageData 分页数据
     * @param data     数据
     */
    public RestResult(int code, String msg, Object pageData, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (Objects.nonNull(pageData)) {
            super.put(DATA_TAG, data);
            if (pageData instanceof Page) {
                super.put(TOTAL_TAG, new PageData((Page) pageData).getTotal());
            }
        }
    }

    public RestResult(int code, String msg, Integer total, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (Objects.nonNull(total)) {
            super.put(TOTAL_TAG, total);
            super.put(DATA_TAG, data);
        }

    }

    /**
     * 把
     * 方便链式调用
     *
     * @param key   关键
     * @param value 价值
     * @return {@link RestResult}
     */
    @Override
    public RestResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 把
     * 方便链式调用
     *
     * @param value 价值
     * @return {@link RestResult}
     */
    public RestResult put(Object value) {
        super.put("data", value);
        return this;
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static RestResult success() {
        return RestResult.success(new ArrayList<>());
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static RestResult success(Object data) {
        return RestResult.success(CommonConstant.SUCCESS, data);
    }

    /**
     * 分页返回成功信息及数据
     */
    public static RestResult success(Object pageData, Object data) {
        return new RestResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, pageData, data);
    }

    public static RestResult success(Integer total, Object data) {
        return new RestResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, total, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static RestResult success(String msg, Object data) {
        return new RestResult(CodeConstant.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static RestResult error() {
        return RestResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static RestResult error(String msg) {
        return RestResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static RestResult error(String msg, Object data) {
        return new RestResult(CodeConstant.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static RestResult error(int code, String msg) {
        return new RestResult(code, msg, null);
    }

    /**
     * com.github.pagehelper.Page 的代理类
     */
    class PageData {
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
