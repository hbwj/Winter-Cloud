package com.winter.cloud.common.security.starter.handler;

import com.winter.cloud.common.core.entity.WinterResponse;
import com.winter.cloud.common.core.utils.WinterUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拒绝访问处理器
 */
public class WinterAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        WinterResponse winterResponse = new WinterResponse();
        WinterUtil.makeJsonResponse(response,HttpServletResponse.SC_FORBIDDEN,winterResponse.message("您没有权限访问该资源"));
    }
}
