package com.tangzq.config;

import com.tangzq.interceptor.ApiInterceptor;
import com.tangzq.interceptor.BaseInterceptor;
import com.tangzq.interceptor.LoginInterceptor;
import com.tangzq.utils.SysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private BaseInterceptor baseInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private ApiInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/cat/**","/user/**","/like/**","/collect/**");
        registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/home","/api/login","/api/register","/api/post/detail/*","/api/comment/public/*");
        log.info("拦截器注册完毕");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ SysUtils.getAbsolutePathOfUploadFolder());
    }
}
