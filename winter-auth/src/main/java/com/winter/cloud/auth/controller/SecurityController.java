package com.winter.cloud.auth.controller;

import com.winter.cloud.common.core.entity.WinterResponse;
import com.winter.cloud.common.core.exception.WinterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public WinterResponse signout(HttpServletRequest request) throws WinterException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer", "");
        WinterResponse WinterResponse = new WinterResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new WinterException("退出登录失败");
        }
        return WinterResponse.message("退出登录成功");
    }
}
