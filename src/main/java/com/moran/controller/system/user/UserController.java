package com.moran.controller.system.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollUtil;
import com.moran.conf.bean.PageResponseBean;
import com.moran.conf.bean.ResponseBean;
import com.moran.controller.system.user.model.UserDTO;
import com.moran.controller.system.user.model.UserVO;
import com.moran.model.SysRole;
import com.moran.model.SysUser;
import com.moran.service.SysRoleService;
import com.moran.service.SysUserService;
import com.moran.util.ServletUtil;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : moran
 */
@RestController
@RequestMapping("/system/user")
@AllArgsConstructor

public class UserController {
    private final SysUserService userService;
    private final SysRoleService roleService;

    /**
     * 删除
     */
    @DeleteMapping("/del")
    @SaCheckPermission("system:user:del")
    public ResponseBean<Boolean> delUser(@Validated @NotNull Long id) {
        userService.delUser(id);
        return ResponseBean.ok(true);
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @SaCheckPermission("system:user:update")
    public ResponseBean<Boolean> updateUser(@Validated(Update.class) @RequestBody UserDTO dto) {
        userService.updateUser(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 新增
     */
    @PostMapping("/create")
    @SaCheckPermission("system:user:create")
    public ResponseBean<Boolean> create(@Validated @RequestBody UserDTO dto) {
        userService.createUser(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 分页
     */
    @GetMapping("/list")
    @SaCheckPermission("system:user:query")
    public PageResponseBean<List<UserVO>> list(String account, String mobile, String name, Long roleId) {
        ServletUtil.startPage();
        List<SysUser> list = userService.list(account, mobile, name, roleId);
        if (CollUtil.isEmpty(list)) {
            return PageResponseBean.ok(list, null);
        }
        Set<Long> roleIds = list.stream().map(SysUser::getRoleIds).flatMap(Collection::stream).collect(Collectors.toSet());
        List<SysRole> roles = roleService.findByRoleIds(roleIds);
        return PageResponseBean.ok(list, list.stream().map(u -> UserVO.convert(u, roles)).toList());
    }
}
