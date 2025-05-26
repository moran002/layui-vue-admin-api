package com.moran.controller.auth.model;

import com.mzt.logapi.starter.annotation.DiffLogAllFields;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@DiffLogAllFields
public class LoginDTO {
    /**
     * 账号
     */
    @NotBlank(message = "请输入账号")
    private String account;
    /**
     * 密码
     */
    @NotBlank(message = "请输入密码")
    private String password;
}
