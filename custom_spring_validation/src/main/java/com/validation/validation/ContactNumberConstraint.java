package com.validation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberConstraint {
    String message()default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload()default {};
}
