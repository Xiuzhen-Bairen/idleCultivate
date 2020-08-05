package com.idleCultivate.util.validation.validator;

import com.idleCultivate.util.validation.annotation.Min;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinValidator implements ConstraintValidator<Min, Number> {
    private long value;

    public MinValidator() {
    }

    public void initialize(Min annotation) {
        this.value = annotation.value();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return false;
        } else {
            return number.longValue() >= value;
        }
    }
}
