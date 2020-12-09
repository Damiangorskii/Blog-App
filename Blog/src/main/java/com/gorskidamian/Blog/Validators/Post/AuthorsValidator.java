package com.gorskidamian.Blog.Validators.Post;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AuthorsValidator implements ConstraintValidator<Authors, String> {

    public void initialize(Authors constraint) {
    }

    @Override
    public boolean isValid(String authors, ConstraintValidatorContext constraintValidatorContext) {

        final String regex = "^[A-Z]+(([',. -][A-Z ])?[a-zA-Z]*)*$";

        return authors.matches(regex) && authors!=null;

    }

}
