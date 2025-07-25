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
import com.moran.conf.exception.ServiceException;
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
import com.moran.utils.ServletUtil;
import com.mzt.logapi.starter.annotation.LogRecord;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录/登录管理
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
        UserInfo userInfo = ServletUtil.getUserInfo();
        StpUtil.logout();
        return ResponseBean.ok(true);
    }
    /**
     * 获取菜单
     * @author : moran
     */
    @GetMapping("/routers")
    @SaIgnore
    public ResponseBean<List<RouterVO> > routers() {
        List<SysMenu> menus = menuService.getAllMenus();
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
    @LogRecord(type = "登录", success = "账号:{{#dto.account}}, 登录成功", bizNo = "0", extra = "{{#dto.toString}}",
            fail = "账号:{{#dto.account}}, 登录失败")
    public ResponseBean<String> login(@RequestBody @Validated LoginDTO dto) {
        String failKey = String.format(CommonConstant.PWD_FAIL_COUNT, dto.getAccount());
        if (redisService.hasKey(failKey)) {
            int count = redisService.get(failKey);
            if (count >= 10) {
                long expire = redisService.getExpire(failKey);
                if (expire < 0) {
                    redisService.expire(failKey, CommonConstant.EXPIRE);
                }
                throw new ServiceException(String.format("密码错误次数过多, 请等待%s秒", expire));
            }
        }
        SysUser sysUser = userService.findByAccount(dto.getAccount());
        if (sysUser == null) {
            throw new ServiceException("用户不存在");
        }
        if (!sysUser.getStatus()) {
            throw new ServiceException("用户已停用");
        }
        if (!CommonConstant.RSA.decryptStr(sysUser.getPassword(), KeyType.PrivateKey).equals(dto.getPassword())) {
            redisService.increment(failKey);
            throw new ServiceException("密码错误");
        }
        redisService.delete(failKey);
        List<SysRole> roles = roleService.findByRoleIds(sysUser.getRoleIds());
        if (CollUtil.isEmpty(roles)) {
            throw new ServiceException("角色已关闭,暂时无法登录");
        }
        List<Long> menuIds = roles.stream().map(SysRole::getMenuIds).flatMap(List::stream).toList();
        List<SysMenu> menus = menuService.findByMenuIds(menuIds);
        if (CollUtil.isEmpty(menus)) {
            throw new ServiceException("暂无目录可访问");
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
