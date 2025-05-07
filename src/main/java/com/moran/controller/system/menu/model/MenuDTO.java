package com.moran.controller.system.menu.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
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
    @NotNull(message = "请选择菜单类型")
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
}
