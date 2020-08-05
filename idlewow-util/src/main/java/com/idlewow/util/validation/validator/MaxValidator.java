package com.idlewow.util.validation.validator;

import com.idlewow.util.validation.annotation.Max;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxValidator implements ConstraintValidator<Max, Number> {
    private long value;

    public MaxValidator() {
    }

    public void initialize(Max annotation) {
        this.value = annotation.value();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return false;
        } else {
            return number.longValue() <= value;
        }
    }
}
