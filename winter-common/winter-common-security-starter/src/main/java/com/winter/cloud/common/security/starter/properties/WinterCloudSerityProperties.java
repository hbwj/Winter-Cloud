package com.winter.cloud.common.security.starter.properties;


import com.winter.cloud.common.core.constant.EndpointConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@ConfigurationProperties(prefix = "windter.cloud.security")
public class WinterCloudSerityProperties {

    /**
     * 是否开启安全配置
     */
    private Boolean enable;

    /**
     * 需要认证的uri，默认位所有/**
     */
    private String authUri = EndpointConstant.ALL;

    /**
     * 免认证的资源路劲，支持通配符
     * 多个值使用逗号分割
     */
    private String anonUris;


    /**
     * 是否只能通过网关配置资源
     * 默认为true
     */
    private Boolean onlyFetchByGateWay=Boolean.TRUE;


    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAuthUri() {
        return authUri;
    }

    public void setAuthUri(String authUri) {
        this.authUri = authUri;
    }

    public String getAnonUris() {
        return anonUris;
    }

    public void setAnonUris(String anonUris) {
        this.anonUris = anonUris;
    }

    public Boolean getOnlyFetchByGateWay() {
        return onlyFetchByGateWay;
    }

    public void setOnlyFetchByGateWay(Boolean onlyFetchByGateWay) {
        this.onlyFetchByGateWay = onlyFetchByGateWay;
    }

    @Override
    public String toString() {
        return "WinterCloudSerityProperties{" +
                "enable=" + enable +
                ", authUri='" + authUri + '\'' +
                ", anonUris='" + anonUris + '\'' +
                ", onlyFetchByGateWay=" + onlyFetchByGateWay +
                '}';
    }
}
