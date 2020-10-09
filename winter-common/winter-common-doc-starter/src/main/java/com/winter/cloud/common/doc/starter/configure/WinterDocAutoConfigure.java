package com.winter.cloud.common.doc.starter.configure;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import com.winter.cloud.common.doc.starter.properties.WinterDocProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@EnableConfigurationProperties(WinterDocProperties.class)
@ConditionalOnProperty(value = "winter.doc.enable",havingValue = "true",matchIfMissing = true)
public class WinterDocAutoConfigure {

    private  WinterDocProperties properties;
    @Autowired
    public void setProperties(WinterDocProperties properties) {
        this.properties = properties;
    }

    public Docket  createRestApiDoc() {
       return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))

                //包下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(securitySchemes());
    }


    private ApiInfo apiInfo() {
        String description = String.format("<div style='font-size:%spx;color:%s;'>%s</div>",
                properties.getDescriptionFontSize(), properties.getDescriptionColor(), properties.getDescription());

        Contact contact = new Contact(properties.getName(), properties.getUrl(), properties.getEmail());

        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(description)
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .contact(contact)
                .license(properties.getLicense())
                .licenseUrl(properties.getLicenseUrl())
                .version(properties.getVersion())
                .build();
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }


    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
    }


    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey("BearerToken", "Authorization", "header"));
    }
}
