package com.moran.controller.system.role.model;

import com.mzt.logapi.starter.annotation.DiffLogAllFields;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@DiffLogAllFields
public class PermissionRoleDTO {
    /**
     *  ID
     */
    @NotNull(message = "请选择角色")
    private Long id;
    /**
     * 权限
     */
    @NotEmpty(message = "请选择权限")
    private List<Long> menuIds;
}
