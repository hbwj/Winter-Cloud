package com.winter.cloud.common.security.starter.configure;


import com.winter.cloud.common.core.constant.StringConstant;
import com.winter.cloud.common.security.starter.handler.WinterAccessDeniedHandler;
import com.winter.cloud.common.security.starter.handler.WinterAuthExceptionEntryPoint;
import com.winter.cloud.common.security.starter.properties.WinterCloudSerityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * oauth2 资源服务器 配置
 */
@EnableResourceServer
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class WinterCloudResourceServerConfigure  extends ResourceServerConfigurerAdapter {

    private WinterCloudSerityProperties properties;
    private WinterAccessDeniedHandler accessDeniedHandler;
    private WinterAuthExceptionEntryPoint exceptionEntryPoint;

    @Autowired
    public void setProperties(WinterCloudSerityProperties properties) {
        this.properties = properties;
    }
    @Autowired
    public void setAccessDeniedHandler(WinterAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }
    @Autowired
    public void setExceptionEntryPoint(WinterAuthExceptionEntryPoint exceptionEntryPoint) {
        this.exceptionEntryPoint = exceptionEntryPoint;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      resources.authenticationEntryPoint(exceptionEntryPoint)
              .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUris(), StringConstant.COMMA);
        if (ArrayUtils.isEmpty(anonUrls)){
            anonUrls = new String[]{};
        }

        http.csrf().disable()
                .requestMatchers().antMatchers(properties.getAuthUri())
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers(properties.getAuthUri()).authenticated()
                .and()
                .httpBasic();
    }

}
