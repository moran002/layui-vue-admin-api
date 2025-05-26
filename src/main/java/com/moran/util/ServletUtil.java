package com.moran.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.moran.conf.bean.UserInfo;
import com.moran.conf.constant.CommonConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author : moran
 */
public class ServletUtil {

    /**
     * 获得请求
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return getUserAgent(request);
    }
    /**
     * @param request 请求
     * @return ua
     */
    public static String getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }

    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return JakartaServletUtil.getClientIP(request);
    }
    public static String getClientIP(HttpServletRequest request) {
        return JakartaServletUtil.getClientIP(request);
    }

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
    /**
     * 获取用户名称
     */
    public static String getLoginUserName() {
        if (getUserInfo() == null) {
            return null;
        }
        return getUserInfo().getNickName();
    }
}
