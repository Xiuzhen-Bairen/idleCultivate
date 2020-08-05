package com.idleCultivate.util.validation.validator;

import com.idleCultivate.util.validation.annotation.Negative;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NegativeValidator implements ConstraintValidator<Negative, Number> {
    public NegativeValidator() {
    }

    public void initialize(Negative annotation) {
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return false;
        } else {
            return number.longValue() < 0;
        }
    }
}
