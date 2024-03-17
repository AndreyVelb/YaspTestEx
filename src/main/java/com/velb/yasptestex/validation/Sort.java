package com.velb.yasptestex.validation;

import com.velb.yasptestex.validation.validator.SortValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {SortValidator.class})
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface Sort {

    String message() default "Вы ввели некорректные данные. " +
            "Использоваться могут только ASC и DESC";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
