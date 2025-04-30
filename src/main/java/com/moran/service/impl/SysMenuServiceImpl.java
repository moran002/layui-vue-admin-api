package com.moran.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moran.model.SysMenu;
import com.moran.mapper.SysMenuMapper;
import com.moran.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-04-30
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> getAllMenus() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<SysMenu> findByMenuIds(List<Long> menuIds) {
        return baseMapper.selectByIds(menuIds);
    }
}
