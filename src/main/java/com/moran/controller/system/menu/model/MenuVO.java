package com.moran.controller.system.menu.model;

import cn.hutool.core.bean.BeanUtil;
import com.moran.model.SysMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuVO {
    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 上级ID
     */
    private Long parentId;

    /**
     * name
     */
    private String name;

    /**
     * 目录
     */
    private String path;

    /**
     * 路由地址
     */
    private String component;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否可见
     */
    private Boolean isShow;

    /**
     * 类型:1:菜单,2:按钮
     */
    private Integer type;

    /**
     * 接口api
     */
    private String api;

    /**
     * 图标
     */
    private String icon;

    private String redirect;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private List<MenuVO> children;

    public static List<MenuVO> convert(List<SysMenu> list) {
        return list.stream().filter(m -> m.getParentId() == null && m.getType() == 1)
                .map(m -> {
                    MenuVO vo = BeanUtil.toBean(m, MenuVO.class);
                    vo.setChildren(findChildren(m.getId(), list));
                    return vo;
                }).toList();
    }

    private static List<MenuVO> findChildren(Long parentId, List<SysMenu> list) {
        return list.stream().filter(m -> parentId.equals(m.getParentId()))
                .map(m -> {
                    MenuVO vo = BeanUtil.toBean(m, MenuVO.class);
                    vo.setChildren(findChildren(m.getId(), list));
                    return vo;
                }).toList();
    }

}
