package com.moran.controller.system.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.bean.PageResponseBean;
import com.moran.conf.bean.ResponseBean;
import com.moran.controller.system.user.model.PasswordDTO;
import com.moran.controller.system.user.model.StatusDTO;
import com.moran.controller.system.user.model.UserDTO;
import com.moran.controller.system.user.model.UserVO;
import com.moran.model.SysRole;
import com.moran.model.SysUser;
import com.moran.service.SysRoleService;
import com.moran.service.SysUserService;
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
 * 系统管理/用户管理
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
     * 状态变更
     */
    @PostMapping("/status")
    @SaCheckPermission("system:user:query")
    public ResponseBean<Boolean> statusUser(@Validated @RequestBody StatusDTO dto) {
        userService.statusUser(dto.getId(), dto.getStatus());
        return ResponseBean.ok(true);
    }

    /**
     * 重置密码
     */
    @PostMapping("/password")
    @SaCheckPermission("system:user:password")
    public ResponseBean<Boolean> passwordUser(@Validated @RequestBody PasswordDTO dto) {
        userService.passwordUser(dto);
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
    public PageResponseBean<UserVO> list(String account, String mobile, String nickName, Long roleId) {
        Page<SysUser> page = userService.userPage(account, mobile, nickName, roleId);
        if (CollUtil.isEmpty(page.getRecords())) {
            return PageResponseBean.ok(page.getTotal(), null);
        }
        Set<Long> roleIds = page.getRecords().stream().map(SysUser::getRoleIds).flatMap(Collection::stream).collect(Collectors.toSet());
        List<SysRole> roles = roleService.findByRoleIds(roleIds);
        return PageResponseBean.ok(page.getTotal(), page.getRecords().stream().map(u -> UserVO.convert(u, roles)).toList());
    }
}
