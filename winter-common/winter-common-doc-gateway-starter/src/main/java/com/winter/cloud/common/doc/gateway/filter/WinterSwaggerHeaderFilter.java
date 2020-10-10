package com.winter.cloud.common.doc.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * 自定义过滤器：处理swagger路径转换
 * swagger 文档中的路径为：主机名：端口：服务路由前缀
 * gateway中的过滤器 StripPrefixGatewayFilterFactory 将原有的服务名前缀被过滤掉了！
 * 因此我们增加X-Forwarder-Prefix 请求头里面的信息为前缀
 */
@SuppressWarnings("all")
public class WinterSwaggerHeaderFilter  extends AbstractGatewayFilterFactory {

    private static final String HEADER_NAME = "X-Forwarded-Prefix";
    private static final String URI = "/v2/api-docs";

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange,chain)->{
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            if(!StringUtils.endsWithIgnoreCase(path,URI)){ //放行
                return chain.filter(exchange);
            }
            String basePath = path.substring(0, path.lastIndexOf(URI));
            //添加请求头
            ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };
    }
}
