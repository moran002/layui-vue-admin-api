package com.moran.controller.system.role.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleVO {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单ID集合
     */
    private List<Long> menuIds;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
