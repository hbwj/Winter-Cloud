package com.winter.cloud.common.doc.gateway.configure;

import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Primary
public class WinterSwaggerResourceProvider  implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public WinterSwaggerResourceProvider(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //取出gateway的routes
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        //过滤路由
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId())).forEach(routeDefinition ->routeDefinition.getPredicates().stream()
        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
        //添加文档来源
        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX+"0")
                        .replace("**","v2/api-docs")))));
        return resources;
    }


    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
