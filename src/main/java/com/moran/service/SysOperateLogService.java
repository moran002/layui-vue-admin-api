package com.moran.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.model.SysOperateLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-05-23
 */
public interface SysOperateLogService extends IService<SysOperateLog> {

    Page<SysOperateLog> logPage(String type, String nickName);
}
