package com.ai_education.config;

import com.ai_education.interceptor.JwtTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        System.out.println("==================开始注册自定义拦截器...............");
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/aiEducation/**")
                .excludePathPatterns("/aiEducation/register","/aiEducation/login","/aiEducation/audio/*","/aiEducation/image/*","/aiEducation/gpt/*","/aiEducation/ppt/*","/aiEducation/currency/*");
    }
}
