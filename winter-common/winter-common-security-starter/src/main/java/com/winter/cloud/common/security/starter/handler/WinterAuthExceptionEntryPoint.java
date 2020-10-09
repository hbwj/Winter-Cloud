package com.winter.cloud.common.security.starter.handler;

import com.winter.cloud.common.core.entity.WinterResponse;
import com.winter.cloud.common.core.utils.WinterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  用来解决匿名用户访问无权限资源时的异常
 */
@Slf4j
public class WinterAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Object requestURI = request.getRequestURI();
        int status =HttpServletResponse.SC_UNAUTHORIZED;
        String msg = "访问令牌不合法";
        log.error("客户端访问{}请求失败：{}",requestURI,msg,authException);
        WinterUtil.makeJsonResponse(response,status,new WinterResponse().message(msg));
    }
}
