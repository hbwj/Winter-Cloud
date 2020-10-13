package com.winter.cloud.server.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WinterServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinterServerTestApplication.class,args);
    }
}


