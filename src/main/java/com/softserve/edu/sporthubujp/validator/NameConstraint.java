package com.softserve.edu.sporthubujp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameConstraint {
    String message() default "Name and surname must contain only letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}