package com.moran.controller.system.dict.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictTypeVO {
    /**
     * 字典主键
     */
    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
