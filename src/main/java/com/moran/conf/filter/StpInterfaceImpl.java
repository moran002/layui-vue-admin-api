package com.moran.conf.filter;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.moran.conf.constant.CommonConstant;
import com.moran.conf.bean.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : moran
 */
@Component
@AllArgsConstructor
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserInfo userInfo = (UserInfo) StpUtil.getSession()
                .get(CommonConstant.USER_INFO);
        return userInfo.getPermissions();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //查询当前用户角色放入集合
        return null;
    }
}
