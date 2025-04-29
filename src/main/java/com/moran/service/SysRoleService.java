package com.moran.service;

import org.springframework.stereotype.Service;
import io.mybatis.service.AbstractService;
import com.moran.model.SysRole;
import com.moran.mapper.SysRoleMapper;

import java.util.Collection;
import java.util.List;

/**
 * sys_role - 角色表
 *
 * @author 系统自动生成
 */
@Service
public class  SysRoleService extends AbstractService<SysRole, Long, SysRoleMapper> {

    public List<SysRole> findByRoleIds(Collection<Long> roleIds) {
        return wrapper().in(SysRole::getId, roleIds).list();
    }
}
