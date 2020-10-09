package com.winter.cloud.common.security.starter.annotation;

import com.winter.cloud.common.security.starter.configure.WinterCloudResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WinterCloudResourceServerConfigure.class)
public @interface EnableWinterCloudResourceServer {
}
