package com.example.spring_pawn_app.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
    String message() default "Ngày bắt đầu không được vượt quá ngày kết thúc";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
