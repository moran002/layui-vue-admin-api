package com.moran.controller.system.menu;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.moran.conf.bean.ResponseBean;
import com.moran.controller.system.menu.model.MenuDTO;
import com.moran.controller.system.menu.model.MenuVO;
import com.moran.controller.system.menu.model.TreeMenuVO;
import com.moran.model.SysMenu;
import com.moran.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 */
@RestController
@RequestMapping("/system/menu")
@AllArgsConstructor
public class MenuController {
    private final SysMenuService sysMenuService;
    /**
     * 删除
     */
    @DeleteMapping("/del")
    @SaCheckPermission("system:menu:del")
    public ResponseBean<Boolean> del(Long id) {
        sysMenuService.removeById(id);
        return ResponseBean.ok(true);
    }
    /**
     * 更新
     */
    @PostMapping("/update")
    @SaCheckPermission("system:menu:update")
    public ResponseBean<Boolean> update(@Validated @RequestBody MenuDTO dto) {
        sysMenuService.updateMenu(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 新增
     */
    @PostMapping("/create")
    @SaCheckPermission("system:menu:create")
    public ResponseBean<Boolean> create(@Validated @RequestBody MenuDTO dto) {
        sysMenuService.createMenu(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @SaCheckPermission("system:menu:query")
    public ResponseBean<List<MenuVO>> list() {
        List<SysMenu> list = sysMenuService.getAllMenus();
        return ResponseBean.ok(MenuVO.convert(list));
    }

    /**
     * 菜单字典
     */
    @GetMapping("/simple-list")
    @SaCheckLogin
    public ResponseBean<List<TreeMenuVO>> simpleList() {
        List<SysMenu> list = sysMenuService.getSimpleList();
        return ResponseBean.ok(TreeMenuVO.convert(list));
    }
}
