package com.moran.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moran.conf.mybatis.LambdaQueryWrapperX;
import com.moran.controller.system.menu.model.MenuDTO;
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

    @Override
    public void createMenu(MenuDTO dto) {
        SysMenu menu = BeanUtil.toBean(dto, SysMenu.class);
        baseMapper.insert(menu);
    }

    @Override
    public void updateMenu(MenuDTO dto) {
        SysMenu menu = BeanUtil.toBean(dto, SysMenu.class);
        baseMapper.updateById(menu);
    }

    @Override
    public List<SysMenu> getSimpleList() {
        return baseMapper.selectList(new LambdaQueryWrapperX<SysMenu>()
                .eq(SysMenu::getIsShow, true));
    }
}
