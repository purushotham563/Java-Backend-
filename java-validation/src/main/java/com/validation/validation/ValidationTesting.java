package com.validation.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidationTesting {

    public static void main(String[] args) {
        ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        User user = new User();
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(50);
        Set<ConstraintViolation<User>>violations=validator.validate(user);
        for(ConstraintViolation<User> violation:violations){
            System.out.print(violation.getMessage());
        }
    }
}
