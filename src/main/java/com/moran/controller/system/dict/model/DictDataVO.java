package com.moran.controller.system.dict.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictDataVO {
    /**
     * 字典编码
     */
    private Long id;

    /**
     * 字典排序
     */
    private Integer sort;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典键值
     */
    private String value;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
