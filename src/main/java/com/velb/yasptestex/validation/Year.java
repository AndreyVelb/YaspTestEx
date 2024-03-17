package com.velb.yasptestex.validation;

import com.velb.yasptestex.validation.validator.YearValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {YearValidator.class})
@Target({PARAMETER})
@Retention(RUNTIME)
public @interface Year {

    String message() default "Вы ввели некорректные данные. " +
            "В данном приложении включен датасет с 1980 по 2023 год. " +
            "Необходимо чтобы значение было в этом диапазоне";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
