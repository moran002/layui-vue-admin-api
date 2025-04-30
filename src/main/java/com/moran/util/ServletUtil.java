package com.moran.util;

import cn.dev33.satoken.stp.StpUtil;
import com.moran.conf.bean.UserInfo;
import com.moran.conf.constant.CommonConstant;

/**
 * @author : moran
 */
public class ServletUtil {

    /**
     * 获取用户ID
     */
    public static UserInfo getUserInfo() {

        return StpUtil.isLogin()? (UserInfo) StpUtil.getSession().get(CommonConstant.USER_INFO): null;
    }
    /**
     * 获取用户ID
     */
    public static Long getLoginUserId() {
        if (getUserInfo() == null) {
            return null;
        }
        return getUserInfo().getId();
    }
}
