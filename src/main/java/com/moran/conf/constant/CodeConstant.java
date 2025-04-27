package com.moran.conf.constant;


/**
 * 返回值常量池
 *
 * @author moran
 */
public final class CodeConstant {
    private CodeConstant() {
        throw new RuntimeException("can not init constant class");
    }

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 登陆异常
     */
    public static final int LOGIN_FAIL = 401;

    /**
     * 失败
     */
    public static final int ERROR = 500;

    /**
     * service异常
     */
    public static final int SERVICE_ERROR = 600;


}
