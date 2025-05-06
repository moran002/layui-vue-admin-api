package com.moran.controller.system.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PasswordDTO {
    /**
     * ID
     */
    @NotNull(message = "请选择用户")
    private Long id;
    /**
     * 密码
     */
    @NotBlank(message = "请输入密码")
    private String password;
    /**
     * 密码2
     */
    private String password2;
}
