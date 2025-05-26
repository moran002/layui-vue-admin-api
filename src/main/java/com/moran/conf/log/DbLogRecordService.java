package com.moran.conf.log;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.moran.model.SysOperateLog;
import com.moran.service.SysOperateLogService;
import com.moran.util.ServletUtil;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DbLogRecordService implements ILogRecordService {
    @Resource
    private SysOperateLogService operateLogService;
    @Override
    public void record(LogRecord logRecord) {
        SysOperateLog log = BeanUtil.toBean(logRecord, SysOperateLog.class);
        log.setBizId(Long.valueOf(logRecord.getBizNo()));
        HttpServletRequest request = ServletUtil.getRequest();
        log.setUserId(ServletUtil.getLoginUserId());
        log.setNickName(ServletUtil.getLoginUserName());
        log.setUserIp(ServletUtil.getClientIP(request));
        log.setUserAgent(ServletUtil.getUserAgent(request));
        log.setRequestMethod(request.getMethod());
        log.setRequestUrl(request.getRequestURI());
        operateLogService.save(log);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        throw new RuntimeException("not implement");
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        throw new RuntimeException("not implement");
    }
}
