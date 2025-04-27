package com.moran.util;

import cn.dev33.satoken.stp.StpUtil;
import com.moran.conf.constant.CommonConstant;
import com.moran.conf.bean.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author : moran
 */
public class ServletUtil {
    /**
     * 开启分页
     */
    public static void startPage() {
        PageUtil.startPage();
    }

    /**
     * 获取request
     * @author :moran
     **/
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取response
     * @author :moran
     **/
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    /**
     * 获取用户设备信息
     * @author :moran
     **/
    public static String getDeviceInfo() {
        return getRequest().getHeader("User-Agent");
    }

    /**
     * 获取用户ID
     */
    public static UserInfo getUserInfo() {
        return (UserInfo) StpUtil.getSession().get(CommonConstant.USER_INFO);
    }
    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUserInfo().getUserId();
    }
}
