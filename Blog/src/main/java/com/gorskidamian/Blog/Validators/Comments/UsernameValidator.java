package com.gorskidamian.Blog.Validators.Comments;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UsernameValidator implements ConstraintValidator<Username, String> {

    public void initialize(Username constraint) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        final String regex = "^[a-zA-Z0-9][a-zA-Z0-9]*[a-zA-Z0-9]$";

        return username.matches(regex) && username.length()>=2 && username.length()<=50;

    }

}
