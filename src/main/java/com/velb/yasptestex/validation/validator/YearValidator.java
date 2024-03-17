package com.velb.yasptestex.validation.validator;

import com.velb.yasptestex.validation.Year;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearValidator implements ConstraintValidator<Year, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            int year;
            try {
                year = Integer.parseInt(value);
            } catch (Exception e) {
                return false;
            }
            return year >= 1980 && year <= 2023;
        } else return true;
    }
}
