package com.moran.controller.system.role.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

@Data
public class RoleDTO {
    /**
     * ID
     */
    @NotNull(message = "请选择角色", groups = Update.class)
    private Long id;
    /**
     * 角色名称
     */
    @NotBlank(message = "请输入角色名称")
    private String name;

    /**
     * 备注
     */
    private String remark;
}
