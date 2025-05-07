package com.moran.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.controller.system.role.model.PermissionRoleDTO;
import com.moran.controller.system.role.model.RoleDTO;
import com.moran.model.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-04-30
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> findByRoleIds(Collection<Long> roleIds);

    List<SysRole> findAll();

    Page<SysRole> pageRole(String name);

    void createRole(RoleDTO dto);

    void updateRole(RoleDTO dto);

    void permissionRole(PermissionRoleDTO dto);

    void delRole(Long id);
}
