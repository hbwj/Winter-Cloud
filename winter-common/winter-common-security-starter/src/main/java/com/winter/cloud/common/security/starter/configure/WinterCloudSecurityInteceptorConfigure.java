package com.winter.cloud.common.security.starter.configure;

import com.winter.cloud.common.security.starter.interceptor.WinterServerProtectInterceptor;
import com.winter.cloud.common.security.starter.properties.WinterCloudSerityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WinterCloudSecurityInteceptorConfigure  implements WebMvcConfigurer {


    private WinterCloudSerityProperties properties;

    @Autowired
    public void setProperties(WinterCloudSerityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor winterServerProtectInterceptor(){
        WinterServerProtectInterceptor interceptor = new WinterServerProtectInterceptor();
        interceptor.setProperties(properties);
        return  interceptor;

    }



    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(winterServerProtectInterceptor());
    }
}
