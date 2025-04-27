package com.moran.mapper;

import io.mybatis.mapper.BaseMapper;

import com.moran.model.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * sys_user - 用户表
 *
 * @author 系统自动生成
 */
@org.apache.ibatis.annotations.Mapper
public interface SysUserMapper extends BaseMapper<SysUser, Long> {

    void incremental(@Param("id") Long id);

    void resetErrorCount(@Param("id") Long id);
}