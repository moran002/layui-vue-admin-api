package com.moran.controller.auth.model;

import lombok.Data;

/**
 * @author : moran
 */
@Data
public class PasswordVO {
    /**
     * 账号
     */
    private String account;
    /**
     * 需要等待的时间
     */
    private Integer time;
    /**
     * 输入错误次数
     */
    private Integer count;
}
