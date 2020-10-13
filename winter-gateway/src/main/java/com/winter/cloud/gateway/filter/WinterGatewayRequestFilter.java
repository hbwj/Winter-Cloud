//package com.winter.cloud.gateway.filter;
//
//import com.winter.cloud.common.core.constant.WinterConstant;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Base64Utils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class WinterGatewayRequestFilter implements GlobalFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        byte[] token = Base64Utils.encode((WinterConstant.GATEWAY_TOKEN_VALUE).getBytes());
//        ServerHttpRequest build = request.mutate().header(WinterConstant.GATEWAY_TOKEN_HEADER, new String(token)).build();
//        ServerWebExchange newExchange = exchange.mutate().request(build).build();
//        return chain.filter(newExchange);
//    }
//
//
//}
