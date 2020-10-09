package com.winter.cloud.common.security.starter.configure;


import com.google.common.net.HttpHeaders;
import com.winter.cloud.common.core.constant.WinterConstant;
import com.winter.cloud.common.core.utils.WinterUtil;
import com.winter.cloud.common.security.starter.handler.WinterAccessDeniedHandler;
import com.winter.cloud.common.security.starter.handler.WinterAuthExceptionEntryPoint;
import com.winter.cloud.common.security.starter.properties.WinterCloudSerityProperties;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(WinterCloudSerityProperties.class)
@ConditionalOnProperty(value = "winter.cloud.security.enable",havingValue = "true",matchIfMissing = true)
public class WinterCloudSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {


    /**
     *  用来解决认证过的用户访问无权限资源时的异常
     */
    @Bean
    @ConditionalOnClass(name = "accessDeniedHandler")
    public WinterAccessDeniedHandler accessDeniedHandler(){
        return new WinterAccessDeniedHandler();
    }


    /**
     *  用来解决匿名用户访问无权限资源时的异常
     */
    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public WinterAuthExceptionEntryPoint AuthenticationEntryPoint(){
        return new WinterAuthExceptionEntryPoint();
    }


    /**
     * 加密
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    /**
     * 拦截器 ：用于处理非网关请求
     * @return
     */
    @Bean
    public  WinterCloudSecurityInteceptorConfigure winterCloudSecurityInteceptorConfigure(){
        return  new WinterCloudSecurityInteceptorConfigure();
    }


    /**
     * RequestInterceptor
     * 微服务之间调用 请求头设置
     * @return
     */
    @Bean
    public RequestInterceptor  feginRequestInterceptor(){

      return  requestTemplate -> {
          //设置请求头

          //设置gatewayToken,默认微服务必须走网关。微服务之间调用需手动设置gatewayToken
          String gatewayToken = new String(Base64Utils.encode(WinterConstant.GATEWAY_TOKEN_VALUE.getBytes()));
          requestTemplate.header(WinterConstant.GATEWAY_TOKEN_HEADER,gatewayToken);

          //设置当前用户的authorizationToken
          String authorizationToken = WinterUtil.getCurrentTokenValue();
          if (StringUtils.isNotBlank(authorizationToken)){
              requestTemplate.header(HttpHeaders.AUTHORIZATION,WinterConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
          }
      };
    }

}
