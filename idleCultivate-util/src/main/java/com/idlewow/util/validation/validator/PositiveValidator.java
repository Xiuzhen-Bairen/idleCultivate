package com.idlewow.util.validation.validator;

import com.idlewow.util.validation.annotation.Positive;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveValidator implements ConstraintValidator<Positive, Number> {
    public PositiveValidator() {
    }

    public void initialize(Positive annotation) {
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return false;
        } else {
            return number.longValue() > 0;
        }
    }
}
