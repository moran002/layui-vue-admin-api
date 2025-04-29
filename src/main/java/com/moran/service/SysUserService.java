package com.moran.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.moran.conf.constant.CommonConstant;
import com.moran.conf.exception.ServiceException;
import com.moran.controller.system.user.model.UserDTO;
import com.moran.mapper.SysUserMapper;
import com.moran.model.SysUser;
import io.mybatis.mapper.example.ExampleWrapper;
import io.mybatis.service.AbstractService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<SysUser> list(String account, String mobile, String nickName, Long roleId) {
        ExampleWrapper<SysUser, Long> wrapper = wrapper();
        if (StrUtil.isNotBlank(account)) {
            wrapper.contains(SysUser::getAccount, account);
        }
        if (StrUtil.isNotBlank(mobile)) {
            wrapper.contains(SysUser::getMobile, mobile);
        }
        if (StrUtil.isNotBlank(nickName)) {
            wrapper.contains(SysUser::getNickName, nickName);
        }
        if (roleId != null) {
            wrapper.anyCondition("json_contains(role_ids, json_array("+ roleId+")");
        }
        wrapper.orderByDesc(SysUser::getId);
        return wrapper.list();
    }

    public void createUser(@Valid UserDTO dto) {
        SysUser user = BeanUtil.toBean(dto, SysUser.class);
        user.setPassword(CommonConstant.RSA.encryptBase64("123456", KeyType.PublicKey));
        saveSelective(user);
    }

    public void updateUser(UserDTO dto) {
        verifyUserExists(dto.getId());
        SysUser user = BeanUtil.toBean(dto, SysUser.class);
        updateSelective(user);
    }

    private void verifyUserExists(Long id) {
        SysUser user = findById(id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
    }

    public void delUser(@NotNull Long id) {
        verifyUserExists(id);

    }
}
