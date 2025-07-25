package com.moran.service;

import com.moran.controller.system.dict.model.DictDataDTO;
import com.moran.model.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-05-27
 */
public interface SysDictDataService extends IService<SysDictData> {

    void createDictData(DictDataDTO dto);

    List<SysDictData> getDataByType(String dictType);

    void updateDictData(DictDataDTO dto);
}
