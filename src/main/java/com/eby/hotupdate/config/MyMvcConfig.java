package com.eby.hotupdate.config;

import com.eby.hotupdate.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /*
    * 非常重要，让bean提前加载
    * */
    @Bean
    public HandlerInterceptor getInterceptor(){
        return new AuthorizationInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor()).addPathPatterns("/api/**");
    }
}
