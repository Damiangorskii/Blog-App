package com.gorskidamian.Blog.Validators.Post;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorsValidator.class)
public @interface Authors {

    String message() default "This authors are not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
