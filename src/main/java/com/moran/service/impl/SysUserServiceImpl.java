package com.moran.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moran.conf.constant.CommonConstant;
import com.moran.conf.exception.ServiceException;
import com.moran.conf.mybatis.LambdaQueryWrapperX;
import com.moran.conf.mybatis.MyBatisUtils;
import com.moran.controller.system.user.model.PasswordDTO;
import com.moran.controller.system.user.model.UserDTO;
import com.moran.mapper.SysUserMapper;
import com.moran.model.SysUser;
import com.moran.service.SysUserService;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author MyBatis-Plus Generator
 * @since 2025-04-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findByAccount(String account) {
        return baseMapper.selectOne(new LambdaQueryWrapper<>(SysUser.class)
                .eq(SysUser::getAccount, account));
    }

    @Override
    @LogRecord(type = "用户管理", subType = "密码变更", success = "成功", bizNo = "{{#user.id}}")
    public void delUser(Long id) {
        SysUser user = verificationUserExist(id);
        LogRecordContext.putVariable("user", user);
        baseMapper.deleteById(id);
    }



    @Override
    public Page<SysUser> userPage(String account, String mobile, String nickName, Long roleId) {
        LambdaQueryWrapperX<SysUser> wrapperX = new LambdaQueryWrapperX<SysUser>()
                .likeIfPresent(SysUser::getAccount, "%" + account + "%")
                .likeIfPresent(SysUser::getMobile, "%" + mobile + "%")
                .likeIfPresent(SysUser::getNickName, "%" + nickName + "%");
        if (roleId != null) {
            wrapperX.apply("json_contains(role_ids, json_array({0}))", roleId);
        }
        return baseMapper.selectPage(MyBatisUtils.buildPage(), wrapperX);
    }

    @Override
    @LogRecord(type = "用户管理", subType = "新增用户", success = "新增用户:{{#dto.toString}}", fail = "新增用户失败", bizNo = "{{#user.id}}")
    public void createUser(UserDTO dto) {
        verificationAccount(dto.getAccount(), null);
        SysUser user = BeanUtil.toBean(dto, SysUser.class);
        user.setPassword(CommonConstant.RSA.encryptBase64("123456", KeyType.PublicKey));
        baseMapper.insert(user);
        LogRecordContext.putVariable("user", user);
    }

    @Override
    @LogRecord(type = "用户管理", subType = "更新用户", success = "用户:{{#user.toString}} -> {{#dto.toString}}", fail = "新增用户失败", bizNo = "{{#user.id}}")
    public void updateUser(UserDTO dto) {
        SysUser sysUser = verificationUserExist(dto.getId());
        LogRecordContext.putVariable("user", sysUser);
        verificationAccount(dto.getAccount(), dto.getId());
        SysUser user = BeanUtil.toBean(dto, SysUser.class);
        user.setPassword(null);
        baseMapper.updateById(user);
    }

    @Override
    @LogRecord(type = "用户管理", subType = "密码变更", success = "成功", bizNo = "{{#user.id}}")
    public void passwordUser(PasswordDTO dto) {
        SysUser user1 = verificationUserExist(dto.getId());
        LogRecordContext.putVariable("user", user1);
        SysUser user = new SysUser();
        user.setId(dto.getId());
        user.setPassword(CommonConstant.RSA.encryptBase64(dto.getPassword(), KeyType.PublicKey));
        baseMapper.updateById(user);
    }

    @Override
    public void statusUser(Long id, Boolean status) {
        verificationUserExist(id);
        SysUser user = new SysUser();
        user.setId(id);
        user.setStatus(status);
        baseMapper.updateById(user);
    }

    private SysUser verificationUserExist(Long id) {
        SysUser user = baseMapper.selectById(id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        return user;
    }

    private void verificationAccount(String account, Long id) {
        SysUser user = baseMapper.selectOne(new LambdaQueryWrapperX<SysUser>().eq(SysUser::getAccount, account));
        if (user == null) {
            return;
        }
        if (!id.equals(user.getId())) {
            throw new ServiceException("当前账号已存在");
        }
    }
}
