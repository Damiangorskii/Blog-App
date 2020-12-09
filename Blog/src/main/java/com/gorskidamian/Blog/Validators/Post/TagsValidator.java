package com.gorskidamian.Blog.Validators.Post;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class TagsValidator implements ConstraintValidator<Tags, String> {

    public void initialize(Tags constraint) {
    }

    @Override
    public boolean isValid(String tags, ConstraintValidatorContext constraintValidatorContext) {

        final String regex = "^[\\D]+(([',. -][A-Z ])?[a-zA-Z]*)*$";

        return tags.matches(regex) && tags!=null && tags.length()<=200;

    }

}
