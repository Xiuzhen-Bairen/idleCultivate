package com.idleCultivate.util.validation.validator;

import com.idleCultivate.util.validation.annotation.NotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullValidator implements ConstraintValidator<NotNull, Object> {
    public NotNullValidator() {
    }

    public void initialize(NotNull annotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return object != null;
    }
}
