package com.moran.service;

import org.springframework.stereotype.Service;
import io.mybatis.service.AbstractService;
import com.moran.model.SysMenu;
import com.moran.mapper.SysMenuMapper;

import java.util.List;

/**
 * sys_menu - 菜单表
 *
 * @author 系统自动生成
 */
@Service
public class  SysMenuService extends AbstractService<SysMenu, Long, SysMenuMapper> {

    public List<SysMenu> findByMenuIds(List<Long> menuIds) {
        return wrapper().in(SysMenu::getId,menuIds).list();
    }
}
