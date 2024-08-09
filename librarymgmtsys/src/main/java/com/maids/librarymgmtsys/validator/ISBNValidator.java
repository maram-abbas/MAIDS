package com.maids.librarymgmtsys.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ISBNValidator implements ConstraintValidator<ValidISBN, String> {

    private static final String ISBN_REGEX = "^(?:\\d{9}[\\dX]|97[89]\\d{9}[\\dX])$";

    @Override
    public void initialize(ValidISBN constraintAnnotation) {
    }

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        if (isbn == null) {
            return false;
        }
        return Pattern.matches(ISBN_REGEX, isbn);
    }
}
