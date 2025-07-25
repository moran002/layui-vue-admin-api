package com.moran.controller.system.log.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogVO {

    /**
     * 日志主键
     */
    private Long id;

    /**
     * 用户名称
     */
    private String nickName;

    /**
     * 操作模块类型
     */
    private String type;

    /**
     * 操作名
     */
    private String subType;

    /**
     * 操作内容
     */
    private String action;

    /**
     * 拓展字段
     */
    private String extra;

    /**
     * 请求方法名
     */
    private String requestMethod;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 用户 IP
     */
    private String userIp;

    /**
     * 浏览器 UA
     */
    private String userAgent;

    private LocalDateTime createTime;
}
