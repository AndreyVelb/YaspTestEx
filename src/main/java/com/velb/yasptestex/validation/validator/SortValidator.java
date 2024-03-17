package com.velb.yasptestex.validation.validator;

import com.velb.yasptestex.validation.Sort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SortValidator implements ConstraintValidator<Sort, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return switch (value) {
            case "ASC", "DESC" -> true;
            default -> false;
        };
    }
}
