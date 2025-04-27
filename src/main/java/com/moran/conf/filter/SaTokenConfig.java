package com.moran.conf.filter;

import cn.dev33.satoken.fun.strategy.SaCorsHandleFunction;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 路由拦截鉴权
 *
 * @author :moran
 **/
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

    /**
     * CORS 跨域处理策略
     */
    @Bean
    public SaCorsHandleFunction corsHandle() {
        return (req, res, sto) -> {
            res.setHeader("Access-Control-Allow-Origin", "*")
                    .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .setHeader("Access-Control-Max-Age", "3600")
                    .setHeader("Access-Control-Allow-Headers", "*");

            SaRouter.match(SaHttpMethod.OPTIONS).free(r -> {
            }).back();
        };
    }

}
