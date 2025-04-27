package com.moran.controller.auth.model;

import cn.hutool.core.bean.BeanUtil;
import com.moran.model.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class UserRouterVO {
    private String id;
    private String title;
    private String icon;
    private List<UserRouterVO> children;

    public static List<UserRouterVO> convert(List<SysMenu> menus) {
        return menus.stream().filter(m -> m.getType() == 1 && m.getParentId() == null)
                .map(m -> {
                    UserRouterVO vo = BeanUtil.toBean(m, UserRouterVO.class);
                    vo.setId(m.getPath());
                    vo.setChildren(findChildren(m.getId(), menus));
                    return vo;
                }).toList();
    }

    private static List<UserRouterVO> findChildren(Long parentId, List<SysMenu> menus) {
        return menus.stream().filter(m -> m.getType() == 1 && parentId.equals(m.getParentId()))
                .map(m -> {
                    UserRouterVO vo = BeanUtil.toBean(m, UserRouterVO.class);
                    vo.setId(m.getPath());
                    vo.setChildren(findChildren(m.getId(), menus));
                    return vo;
                }).toList();
    }
}
