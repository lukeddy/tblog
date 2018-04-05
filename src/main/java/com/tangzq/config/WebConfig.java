package com.tangzq.config;

import com.tangzq.interceptor.BaseInterceptor;
import com.tangzq.interceptor.LoginInterceptor;
import com.tangzq.utils.SysUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Slf4j
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/cat/**","/topic/**","/user/**","/like/**","/collect/**");
        super.addInterceptors(registry);
        log.info("拦截器注册完毕");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ SysUtils.getAbsolutePathOfUploadFolder());
        super.addResourceHandlers(registry);
    }
}
