package com.moran.service;

import com.moran.model.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-04-30
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> getAllMenus();

    List<SysMenu> findByMenuIds(List<Long> menuIds);
}
