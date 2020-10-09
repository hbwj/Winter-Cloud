package com.winter.cloud.common.redis.starter.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "winter.lettuce.redis")
public class WinterLettuceRedisProperties {

    /**
     * 是否开启Lettuce Redis
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "WinterLettuceRedisProperties{" +
                "enable=" + enable +
                '}';
    }
}
