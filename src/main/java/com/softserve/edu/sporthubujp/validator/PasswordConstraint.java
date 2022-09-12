package com.softserve.edu.sporthubujp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidatorDTO.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Service: password must contain at least 8 characters (letters and numbers)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
