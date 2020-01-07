package com.cal.calorier.config;
import com.cal.calorier.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
/**
 * 拦截器配置
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    private TokenInterceptor tokenInterceptor;
    //构造方法
    public IntercepterConfig(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //user
        String addPathPattern1="/calorier/apis/v1**";
        String [] excludePath1={
                "/calorier/apis/v1/uploadfood"
        };
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(addPathPattern1)
                .excludePathPatterns(excludePath1);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
