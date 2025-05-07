package com.moran.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moran.conf.exception.ServiceException;
import com.moran.conf.mybatis.LambdaQueryWrapperX;
import com.moran.conf.mybatis.MyBatisUtils;
import com.moran.controller.system.role.model.PermissionRoleDTO;
import com.moran.controller.system.role.model.RoleDTO;
import com.moran.mapper.SysRoleMapper;
import com.moran.model.SysRole;
import com.moran.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-04-30
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> findByRoleIds(Collection<Long> roleIds) {
        return baseMapper.selectByIds(roleIds);
    }

    @Override
    public List<SysRole> findAll() {
        return baseMapper.selectList(new LambdaQueryWrapper<>(SysRole.class));
    }

    @Override
    public Page<SysRole> pageRole(String name) {
        return baseMapper.selectPage(MyBatisUtils.buildPage(), new LambdaQueryWrapperX<SysRole>()
                .likeIfPresent(SysRole::getName, name));
    }

    @Override
    public void createRole(RoleDTO dto) {
        verificationName(null, dto.getName());
        SysRole role = BeanUtil.toBean(dto, SysRole.class);
        baseMapper.insert(role);
    }

    @Override
    public void updateRole(RoleDTO dto) {
        verificationRole(dto.getId());
        verificationName(dto.getId(), dto.getName());
        SysRole role = BeanUtil.toBean(dto, SysRole.class);
        baseMapper.updateById(role);
    }

    @Override
    public void permissionRole(PermissionRoleDTO dto) {
        verificationRole(dto.getId());
        SysRole sysRole = new SysRole();
        sysRole.setId(dto.getId());
        sysRole.setMenuIds(dto.getMenuIds());
        baseMapper.updateById(sysRole);
    }

    @Override
    public void delRole(Long id) {
        verificationRole(id);
        baseMapper.deleteById(id);
    }

    private void verificationRole(Long id) {
        SysRole role = baseMapper.selectById(id);
        if (role == null) {
            throw new ServiceException("请选择角色");
        }
    }

    private void verificationName(Long id, String name) {
        SysRole sysRole = baseMapper.selectOne(new LambdaQueryWrapperX<SysRole>().eq(SysRole::getName, name));
        if (sysRole == null) {
            return;
        }
        if (!sysRole.getId().equals(id)) {
            throw new ServiceException("当前角色名称已存在");
        }
    }
}
