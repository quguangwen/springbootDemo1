package com.demo.config;


import com.demo.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestTemplate;

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
