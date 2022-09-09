package com.softserve.edu.sporthubujp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameConstraint, String> {

    @Override
    public void initialize(NameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext constraintValidatorContext) {
        return nameField.matches("^[A-Za-z]{1,32}$");
    }
}
