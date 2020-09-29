package com.winter.cloud.common.core.validator;

import com.winter.cloud.common.core.annotation.IsMobile;
import com.winter.cloud.common.core.constant.RegexpConstant;
import com.winter.cloud.common.core.utils.WinterUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile, String> {
    @Override
    public void initialize(IsMobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE;
                return WinterUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
