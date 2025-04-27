package com.moran.controller.system.user.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.moran.model.SysRole;
import com.moran.model.SysUser;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author : moran
 */
@Data
public class UserVO {
    /**
     * ID
     */
    private Long id;
    /**
     * 账号
     */
    private String account;

    /**
     * 实际姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色ID集合
     */
    private List<Long> roleId;
    private String roleNames;

    /**
     * 状态:0:关闭,1:正常
     */
    private Boolean status;

    public static UserVO convert(SysUser u, List<SysRole> roles) {
        UserVO vo = BeanUtil.toBean(u, UserVO.class);
        if (CollUtil.isNotEmpty(roles)) {
            vo.setRoleNames(roles.stream().filter(r -> u.getRoleIds().contains(r.getId())).map(SysRole::getName).collect(Collectors.joining(",")));
        }
        return vo;
    }
}
