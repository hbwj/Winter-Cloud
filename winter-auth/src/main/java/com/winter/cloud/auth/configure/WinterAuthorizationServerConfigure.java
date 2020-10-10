package com.winter.cloud.auth.configure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor //生成所有带final字段的构造函数 自动注入属性 省略@autowired注解
public class WinterAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

}
