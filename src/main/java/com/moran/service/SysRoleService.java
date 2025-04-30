package com.moran.service;

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
}
