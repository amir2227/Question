package com.mvp.question.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class)
@Documented
public @interface FileSize {
    Class<? extends Payload> [] payload() default{};
    Class<?>[] groups() default {};
    long maxSizeInMB() default 512;
    
    String message() default "Max file size exceed.";
}
