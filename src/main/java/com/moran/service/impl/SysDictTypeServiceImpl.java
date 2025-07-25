package com.moran.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.exception.ServiceException;
import com.moran.conf.mybatis.LambdaQueryWrapperX;
import com.moran.conf.mybatis.MyBatisUtils;
import com.moran.controller.system.dict.model.DictTypeDTO;
import com.moran.model.SysDictType;
import com.moran.mapper.SysDictTypeMapper;
import com.moran.service.SysDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-05-27
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Override
    public Page<SysDictType> dictPage(String name, String type) {
        return baseMapper.selectPage(MyBatisUtils.buildPage(),new LambdaQueryWrapperX<SysDictType>()
                .orderByDesc(SysDictType::getCreateTime)
                .eqIfPresent(SysDictType::getName, name)
                .eqIfPresent(SysDictType::getType, type));
    }

    @Override
    public void createDictType(DictTypeDTO dto) {
        SysDictType sysDictType = selectByType(dto.getType());
        if (sysDictType != null) {
            throw new ServiceException("字典类型已存在");
        }
        SysDictType dictType = BeanUtil.toBean(dto, SysDictType.class);
        baseMapper.insert(dictType);
    }

    @Override
    @LogRecord(type = "字典管理", subType = "更新字典类型", success = "成功", bizNo = "{{#dto.id}}",
    extra = "数据:{{#dictType.toString}} -> {{#dto.toString}}")
    public void updateDictType(DictTypeDTO dto) {
        SysDictType dictType = getById(dto.getId());
        if (dictType == null) {
            throw new ServiceException("当前字典不存在");
        }
        if (!dto.getType().equals(dictType.getType())) {
            SysDictType sysDictType = selectByType(dto.getType());
            if (sysDictType != null) {
                throw new ServiceException("当前类型已存在,无法更改");
            }
        }
        LogRecordContext.putVariable("dictType", dictType);
        SysDictType sysDictType = BeanUtil.toBean(dto, SysDictType.class);
        baseMapper.updateById(sysDictType);
    }

    public SysDictType selectByType(String type) {
        return baseMapper.selectOne(new LambdaQueryWrapperX<SysDictType>().eq(SysDictType::getType, type));
    }
}
