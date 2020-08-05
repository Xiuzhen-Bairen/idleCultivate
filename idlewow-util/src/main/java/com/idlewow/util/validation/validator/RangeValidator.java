package com.idlewow.util.validation.validator;

import com.idlewow.util.validation.annotation.Range;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<Range, Number> {
    private long min;
    private long max;

    public RangeValidator() {
    }

    public void initialize(Range annotation) {
        this.min = annotation.min();
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return false;
        } else {
            return number.longValue() >= this.min && number.longValue() <= this.max;
        }
    }
}
