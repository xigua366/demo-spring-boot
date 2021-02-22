package com.yx.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SexValidator implements ConstraintValidator<Sex, Byte> {



    @Override
    public void initialize(Sex constraintAnnotation) {

    }

    /**
     *
     * 校验的实现逻辑
     */
    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        return value == 0 || value == 1;

    }
}
