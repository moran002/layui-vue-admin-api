package com.moran.conf.bean;

import com.moran.model.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户信息
 * @author : moran
 */
@Setter
@Getter
public class UserInfo {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 手机号
     */
    private String email;
    /**
     * 菜单
     */
    private List<SysMenu> menus;
    /**
     * api
     */
    private List<String> permissions;

}
