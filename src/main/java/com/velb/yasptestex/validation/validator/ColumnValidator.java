package com.velb.yasptestex.validation.validator;

import com.velb.yasptestex.model.parameter.ColumnParameter;
import com.velb.yasptestex.validation.Column;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ColumnValidator implements ConstraintValidator<Column, String> {

    private final ColumnParameter columnParameter;


    @Override
    public boolean isValid(String userVal, ConstraintValidatorContext context) {
        return columnParameter.isThisKeyInMap(userVal);
    }

}
