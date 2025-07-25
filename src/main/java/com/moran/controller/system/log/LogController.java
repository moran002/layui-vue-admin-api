package com.moran.controller.system.log;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.bean.PageResponseBean;
import com.moran.controller.system.log.model.LogVO;
import com.moran.model.SysOperateLog;
import com.moran.service.SysOperateLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理/日志管理
 */
@RestController
@RequestMapping("/system/log")
@AllArgsConstructor
public class LogController {
    private final SysOperateLogService sysOperateLogService;

    @GetMapping("/list")
    @SaCheckPermission("system:log:query")
    public PageResponseBean<LogVO> list(String type, String nickName) {
        Page<SysOperateLog> page = sysOperateLogService.logPage(type, nickName);
        return PageResponseBean.ok(page.getTotal(), page.getRecords().stream().map(l -> BeanUtil.toBean(l, LogVO.class)).toList());
    }
}
