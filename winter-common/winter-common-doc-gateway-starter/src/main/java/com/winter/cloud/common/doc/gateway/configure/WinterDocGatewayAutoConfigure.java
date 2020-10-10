package com.winter.cloud.common.doc.gateway.configure;

import com.winter.cloud.common.doc.gateway.filter.WinterSwaggerHeaderFilter;
import com.winter.cloud.common.doc.gateway.handler.WinterSwaggerHandler;
import com.winter.cloud.common.doc.gateway.properties.WinterDocGatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;

@Configuration
@EnableConfigurationProperties(WinterDocGatewayProperties.class)
@ConditionalOnProperty(value = "winter.doc.gateway.enable",havingValue = "true",matchIfMissing = true)
public class WinterDocGatewayAutoConfigure {

    private final WinterDocGatewayProperties winterDocGatewayProperties;
    private SecurityConfiguration securityConfiguration;
    private UiConfiguration uiConfiguration;

//    @Autowired
    public WinterDocGatewayAutoConfigure(WinterDocGatewayProperties winterDocGatewayProperties) {
        this.winterDocGatewayProperties = winterDocGatewayProperties;
    }

    @Autowired(required = false)
    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }
    @Autowired(required = false)
    public void setUiConfiguration(UiConfiguration uiConfiguration) {
        this.uiConfiguration = uiConfiguration;
    }

    @Bean
    public  WinterSwaggerResourceProvider winterSwaggerResourceProvider(RouteLocator routeLocator, GatewayProperties gatewayProperties){
        return  new WinterSwaggerResourceProvider(routeLocator,gatewayProperties);
    }

    @Bean
    public WinterSwaggerHeaderFilter winterSwaggerHeaderFilter(){
        return  new WinterSwaggerHeaderFilter();
    }

    @Bean
    public WinterSwaggerHandler winterSwaggerHandler(SwaggerResourcesProvider swaggerResourcesProvider){
        WinterSwaggerHandler winterSwaggerHandler = new WinterSwaggerHandler();
        winterSwaggerHandler.setSecurityConfiguration(securityConfiguration);
        winterSwaggerHandler.setUiConfiguration(uiConfiguration);
        winterSwaggerHandler.setSwaggerResourcesProvider(swaggerResourcesProvider);
        winterSwaggerHandler.setProperties(winterDocGatewayProperties);
        return  winterSwaggerHandler;
    }
}
