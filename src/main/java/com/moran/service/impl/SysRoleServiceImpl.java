package com.moran.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.moran.model.SysRole;
import com.moran.mapper.SysRoleMapper;
import com.moran.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
