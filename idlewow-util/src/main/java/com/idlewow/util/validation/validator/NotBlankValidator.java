package com.idlewow.util.validation.validator;

import com.idlewow.util.validation.annotation.NotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotBlankValidator implements ConstraintValidator<NotBlank, CharSequence> {
    public NotBlankValidator() {
    }

    public void initialize(NotBlank annotation) {
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) {
            return false;
        } else {
            return charSequence.toString().trim().length() > 0;
        }
    }
}
