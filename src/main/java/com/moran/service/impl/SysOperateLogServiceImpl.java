package com.moran.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.mybatis.LambdaQueryWrapperX;
import com.moran.conf.mybatis.MyBatisUtils;
import com.moran.model.SysOperateLog;
import com.moran.mapper.SysOperateLogMapper;
import com.moran.service.SysOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-05-23
 */
@Service
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogMapper, SysOperateLog> implements SysOperateLogService {

    @Override
    public Page<SysOperateLog> logPage(String type, String nickName) {
        return baseMapper.selectPage(MyBatisUtils.buildPage(), new LambdaQueryWrapperX<SysOperateLog>()
                .orderByDesc(SysOperateLog::getCreateTime)
                .likeIfPresent(SysOperateLog::getNickName, nickName)
                .likeIfPresent(SysOperateLog::getType, type));
    }
}
