package com.moran.conf.bean;

import com.moran.conf.constant.CodeConstant;
import com.moran.conf.constant.CommonConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : moran
 */
@Setter
@Getter
public class ResponseBean<T> {
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

    public static <T> ResponseBean<T> ok() {
        return createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, null);
    }

    public static <T> ResponseBean<T> ok(T data) {
        ResponseBean<T> result = createResult(CodeConstant.SUCCESS, CommonConstant.SUCCESS, data);
        return result;
    }

    public static <T> ResponseBean<T> fail(Integer code, String msg) {
        return createResult(code, msg, null);
    }

    public static <T> ResponseBean<T> fail(String msg) {
        return createResult(CodeConstant.ERROR, msg, null);
    }
    public static <T> ResponseBean<T> loginFail(String msg) {
        return createResult(CodeConstant.LOGIN_FAIL, msg, null);
    }

    public static <T> ResponseBean<T> createResult(int code, String msg, T data) {
        ResponseBean<T> result = new ResponseBean<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
