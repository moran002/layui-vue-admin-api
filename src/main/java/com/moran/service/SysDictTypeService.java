package com.moran.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.controller.system.dict.model.DictTypeDTO;
import com.moran.model.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-05-27
 */
public interface SysDictTypeService extends IService<SysDictType> {

    Page<SysDictType> dictPage(String name, String type);

    void createDictType(DictTypeDTO dto);

    void updateDictType(DictTypeDTO dto);
}
