package com.moran.controller.system.role;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.bean.OptionVO;
import com.moran.conf.bean.PageResponseBean;
import com.moran.conf.bean.ResponseBean;
import com.moran.controller.system.role.model.PermissionRoleDTO;
import com.moran.controller.system.role.model.RoleDTO;
import com.moran.controller.system.role.model.RoleVO;
import com.moran.model.SysRole;
import com.moran.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理/角色管理
 */
@RestController
@RequestMapping("/system/role")
@AllArgsConstructor
public class RoleController {
    private final SysRoleService sysRoleService;

    /**
     * 删除
     */
    @DeleteMapping("/del")
    @SaCheckPermission("system:role:del")
    public ResponseBean<Boolean> delRole(Long id) {
        sysRoleService.delRole(id);
        return ResponseBean.ok(true);
    }

    /**
     * 权限
     */
    @PostMapping("/permission")
    @SaCheckPermission("system:role:permission")
    public ResponseBean<Boolean> permissionRole(@Validated @RequestBody PermissionRoleDTO dto) {
        sysRoleService.permissionRole(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @SaCheckPermission("system:role:update")
    public ResponseBean<Boolean> updateRole(@Validated(Update.class) @RequestBody RoleDTO dto) {
        sysRoleService.updateRole(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 新增
     */
    @PostMapping("/create")
    @SaCheckPermission("system:role:create")
    public ResponseBean<Boolean> createRole(@Validated @RequestBody RoleDTO dto) {
        sysRoleService.createRole(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @SaCheckPermission("system:role:query")
    public PageResponseBean<RoleVO> list(String name) {
        Page<SysRole> page = sysRoleService.pageRole(name);
        return PageResponseBean.ok(page.getTotal(), page.getRecords().stream().map(r -> BeanUtil.toBean(r, RoleVO.class)).toList());
    }

    /**
     * 角色字典
     */
    @GetMapping("/simple-list")
    @SaCheckLogin
    public ResponseBean<List<OptionVO>> simpleList() {
        List<SysRole> roles = sysRoleService.findAll();
        return ResponseBean.ok(roles.stream().map(r -> OptionVO.convert(r.getId(), r.getName())).toList());
    }
}
