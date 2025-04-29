package com.moran.controller.system.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Data
public class UserDTO {
    /**
     * ID
     */
    @NotNull(message = "请选择用户", groups = Update.class)
    private Long id;

    /**
     * 账号
     */
    @NotBlank(message = "请输入账号")
    private String account;

    /**
     * 实际姓名
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色ID集合
     */
    @NotEmpty(message = "请选择角色")
    private List<Long> roleIds;

}
