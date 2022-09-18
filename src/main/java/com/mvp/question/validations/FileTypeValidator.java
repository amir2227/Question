package com.mvp.question.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileTypeValidator implements ConstraintValidator<IsValidFileType, MultipartFile> {

    @Override
    public void initialize(IsValidFileType constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        boolean result = true;
        String contentType = value.getContentType();
        if (!isSupportedContentType(contentType)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "only valid file are allowed.")
                    .addConstraintViolation();
            result = false;
        }
        return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg")
                || contentType.equals("application/msword");
    }
}
