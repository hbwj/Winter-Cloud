package com.winter.cloud.common.security.starter.interceptor;

import com.winter.cloud.common.core.constant.WinterConstant;
import com.winter.cloud.common.core.entity.WinterResponse;
import com.winter.cloud.common.core.utils.WinterUtil;
import com.winter.cloud.common.security.starter.properties.WinterCloudSerityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

/**
 * 拦截器：根据是否配置拦截非网关请求
 */
public class WinterServerProtectInterceptor implements HandlerInterceptor {

    private WinterCloudSerityProperties properties;

    public void setProperties(WinterCloudSerityProperties properties) {
        this.properties = properties;
    }

    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (!properties.getOnlyFetchByGateWay()){
            return  true;
        }

        String token = request.getHeader(WinterConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(WinterConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(gatewayToken,token)){
            return true;
        }else {
            WinterResponse  resp = new WinterResponse();
            WinterUtil.makeJsonResponse(response,HttpServletResponse.SC_FORBIDDEN,resp.message("请通过网关请求"));
            return false;
        }
    }



}
