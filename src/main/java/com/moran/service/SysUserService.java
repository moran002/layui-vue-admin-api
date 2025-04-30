package com.moran.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moran.controller.system.user.model.UserDTO;
import com.moran.model.SysUser;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-04-30
 */
public interface SysUserService extends IService<SysUser> {

    SysUser findByAccount(String account);

    void delUser(@NotNull Long id);

    Page<SysUser> userPage(String account, String mobile, String nickName, Long roleId);

    void createUser(UserDTO dto);

    void updateUser(UserDTO dto);
}
