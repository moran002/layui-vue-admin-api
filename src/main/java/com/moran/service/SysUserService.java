package com.moran.service;

import com.moran.mapper.SysUserMapper;
import com.moran.model.SysUser;
import io.mybatis.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * sys_user - 用户表
 *
 * @author 系统自动生成
 */
@Service
public class SysUserService extends AbstractService<SysUser, Long, SysUserMapper> {

    public Optional<SysUser> findByAccount(String account) {
        return wrapper().eq(SysUser::getAccount, account)
                .one();
    }

    public void incremental(Long id) {
        baseMapper.incremental(id);
    }

    public void resetErrorCount(Long id) {
        baseMapper.resetErrorCount(id);
    }
}
