package com.gorskidamian.Blog.Validators.Comments;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CommentContentValidator implements ConstraintValidator<CommentContent, String> {

    public void initialize(CommentContent constraint) {
    }

    @Override
    public boolean isValid(String commentContent, ConstraintValidatorContext constraintValidatorContext) {


        return commentContent.length()>=2 && commentContent.length()<=300;

    }

}
