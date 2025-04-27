package com.moran.controller.auth;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.moran.conf.bean.ResponseBean;
import com.moran.conf.bean.UserInfo;
import com.moran.conf.constant.CommonConstant;
import com.moran.conf.redis.RedisService;
import com.moran.controller.auth.model.LoginDTO;
import com.moran.controller.auth.model.RouterVO;
import com.moran.controller.auth.model.UserRouterVO;
import com.moran.model.SysMenu;
import com.moran.model.SysRole;
import com.moran.model.SysUser;
import com.moran.service.SysMenuService;
import com.moran.service.SysRoleService;
import com.moran.service.SysUserService;
import com.moran.util.ServletUtil;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 登录管理
 * @author : moran
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Validated
public class AuthController {
    private final RedisService redisService;
    private final SysUserService userService;
    private final SysRoleService roleService;
    private final SysMenuService menuService;

    /**
     * 登出
     * @author : moran
     */
    @PostMapping("/logout")
    @SaCheckLogin
    public ResponseBean<Boolean> logout() {
        StpUtil.logout();
        return ResponseBean.ok(true);
    }
    /**
     * 获取菜单
     * @author : moran
     */
    @GetMapping("/routers")
    @SaCheckLogin
    public ResponseBean<List<RouterVO> > routers() {
        List<SysMenu> menus = ServletUtil.getUserInfo().getMenus();
        return ResponseBean.ok(RouterVO.convert(menus));
    }

    /**
     * 获取用户路由
     */
    @GetMapping("/userRouters")
    @SaCheckLogin
    public ResponseBean<List<UserRouterVO>> userRouters() {
        List<SysMenu> menus = ServletUtil.getUserInfo().getMenus();
        return ResponseBean.ok(UserRouterVO.convert(menus));
    }

    /**
     * 获取权限
     */
    @GetMapping("/permissions")
    @SaCheckLogin
    public ResponseBean<List<String>> permissions() {
        return ResponseBean.ok(ServletUtil.getUserInfo().getPermissions());
    }

    /**
     * 获取用户信息
     * @author : moran
     */
    @GetMapping("/userInfo")
    @SaCheckLogin
    public ResponseBean<UserInfo> getUserInfo() {
        UserInfo userInfo = ServletUtil.getUserInfo();
        userInfo.setMenus(null);
        return ResponseBean.ok(userInfo);
    }

    /**
     * 登录
     * @author : moran
     */
    @PostMapping("/login")
    @SaIgnore
    public ResponseBean<String> login(@RequestBody @Validated LoginDTO dto) {
        String failKey = String.format(CommonConstant.PWD_FAIL_COUNT, dto.getAccount());
        if (redisService.hasKey(failKey)) {
            return ResponseBean.fail(String.format("密码多次错误,请等待%s秒之后再试", redisService.getExpire(failKey)));
        }
        Optional<SysUser> optional = userService.findByAccount(dto.getAccount());
        if (optional.isEmpty()) {
            return ResponseBean.fail("用户不存在");
        }
        SysUser sysUser = optional.get();
        if (!sysUser.getStatus()) {
            return ResponseBean.fail("用户已停用");
        }
        if (!CommonConstant.RSA.decryptStr(sysUser.getPassword(), KeyType.PublicKey).equals(dto.getPassword())) {
            userService.incremental(sysUser.getId());
            if (sysUser.getErrorCount() >= 5) {
                long l = 60 * (long) (Math.pow(2, (sysUser.getErrorCount() - 5)));
                redisService.set(failKey, dto.getAccount(), l);
                return ResponseBean.fail(String.format("密码错误次数过多, 请等待%s秒", l));
            }
            return ResponseBean.fail("密码错误");
        }
        userService.resetErrorCount(sysUser.getId());
        List<SysRole> roles = roleService.findByRoleIds(sysUser.getRoleIds());
        if (CollUtil.isEmpty(roles)) {
            return ResponseBean.fail("角色已关闭,暂时无法登录");
        }
        List<Long> menuIds = roles.stream().map(SysRole::getMenuIds).flatMap(List::stream).toList();
        List<SysMenu> menus = menuService.findByMenuIds(menuIds);
        if (CollUtil.isEmpty(menus)) {
            return ResponseBean.fail("暂无目录可访问");
        }
        UserInfo userInfo = BeanUtil.toBean(sysUser, UserInfo.class);
        userInfo.setPermissions(menus.stream()
                .map(SysMenu::getApi)
                .filter(StrUtil::isNotBlank)
                .toList());
        userInfo.setMenus(menus);
        StpUtil.login(sysUser.getId());
        StpUtil.getSession().set(CommonConstant.USER_INFO, userInfo);
        return ResponseBean.ok(StpUtil.getTokenValue());
    }
}
