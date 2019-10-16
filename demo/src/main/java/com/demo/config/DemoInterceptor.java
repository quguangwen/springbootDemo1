package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DemoInterceptor implements WebMvcConfigurer {
    @Autowired
    ParamInterceptor paramInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paramInterceptor).addPathPatterns("/**");
    }

    @Bean
    public ParamInterceptor getParamInterceptor(){
        return new ParamInterceptor();
    }
}
