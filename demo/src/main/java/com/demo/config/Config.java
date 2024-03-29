package com.demo.config;

import com.demo.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
* 注册和配置外部Bean，用于springboot启动装载，注入。
* */
@Configuration
public class Config {

    @Bean
    public FinupLend getFinupLend(){
        return new FinupLend();
    }
    @Bean
    public AttachBean getAttachBean(){
        return new AttachBean();
    }
    @Bean
    public Sale getSale(){
        return new Sale();
    }
    @Bean
    public AppLendInfor getApplendInfor(){
        return new AppLendInfor();
    }
    @Bean
    public AppCustomerVo getAppCustomerVo(){
        return new AppCustomerVo();
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    public BChanelBean BChanelBean(){
        return new BChanelBean();
    }

}
