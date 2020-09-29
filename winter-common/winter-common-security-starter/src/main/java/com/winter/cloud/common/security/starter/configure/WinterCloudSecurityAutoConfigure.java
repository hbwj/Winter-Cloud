package com.winter.cloud.common.security.starter.configure;


import com.winter.cloud.common.security.starter.handler.WinterAccessDeniedHandler;
import com.winter.cloud.common.security.starter.handler.WinterAuthExceptionEntryPoint;
import com.winter.cloud.common.security.starter.properties.WinterCloudSerityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(WinterCloudSerityProperties.class)
@ConditionalOnProperty(value = "winter.cloud.security.enable",havingValue = "true",matchIfMissing = true)
public class WinterCloudSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {


    @Bean
    @ConditionalOnClass(name = "accessDeniedHandler")
    public WinterAccessDeniedHandler accessDeniedHandler(){
        return new WinterAccessDeniedHandler();
    }


    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public WinterAuthExceptionEntryPoint AuthenticationEntryPoint(){
        return new WinterAuthExceptionEntryPoint();
    }



}
