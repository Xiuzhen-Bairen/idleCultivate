package com.idleCultivate.util.validation.validator;

import com.idleCultivate.util.validation.annotation.Size;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeValidator implements ConstraintValidator<Size, CharSequence> {
    private int min;
    private int max;

    public SizeValidator() {
    }

    public void initialize(Size annotation) {
        this.min = annotation.min();
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) {
            return false;
        } else {
            int size = charSequence.length();
            return size >= min && size <= max;
        }
    }
}
