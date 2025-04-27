package com.moran.controller.system.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Data
public class UserDTO {

    /**
     * 账号
     */
    @NotBlank(message = "请输入账号")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "请输入密码", groups = Insert.class)
    private String password;

    /**
     * 实际姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色ID集合
     */
    @NotEmpty(message = "请选择角色")
    private List<Long> roleIds;

    /**
     * 状态:0:关闭,1:正常
     */
    private Boolean status;

}
