package com.moran.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.moran.conf.exception.ServiceException;
import com.moran.conf.mybatis.LambdaQueryWrapperX;
import com.moran.controller.system.dict.vo.DictDataDTO;
import com.moran.model.SysDictData;
import com.moran.mapper.SysDictDataMapper;
import com.moran.service.SysDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-05-27
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    @Override
    public void updateDictData(DictDataDTO dto) {
        SysDictData data = getById(dto.getId());
        if (data == null) {
            throw new RuntimeException("当前数据不存在");
        }
        if (!dto.getValue().equals(data.getValue())) {
            SysDictData sysDictData = selectDataOne(dto.getValue(), dto.getDictType());
            if (sysDictData != null) {
                throw new ServiceException("当前数据已存在");
            }
        }
        SysDictData dictData = BeanUtil.toBean(dto, SysDictData.class);
        updateById(dictData);
    }

    @Override
    public void createDictData(DictDataDTO dto) {
        SysDictData sysDictData = selectDataOne(dto.getValue(), dto.getDictType());
        if (sysDictData != null) {
            throw new ServiceException("当前数据已存在");
        }
        SysDictData data = BeanUtil.toBean(dto, SysDictData.class);
        save(data);
    }

    @Override
    public List<SysDictData> getDataByType(String dictType) {
        return baseMapper.selectList(new LambdaQueryWrapperX<SysDictData>()
                .orderByDesc(SysDictData::getCreateTime)
                .eq(SysDictData::getDictType, dictType));
    }



    private SysDictData selectDataOne(String value, String dictType) {
        return baseMapper.selectOne(new LambdaQueryWrapperX<SysDictData>()
                .eq(SysDictData::getDictType, dictType)
                .eq(SysDictData::getValue, value));
    }
}
