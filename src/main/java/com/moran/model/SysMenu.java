package com.moran.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-04-30
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_menu", autoResultMap = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;
}
