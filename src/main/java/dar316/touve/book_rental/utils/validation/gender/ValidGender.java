package dar316.touve.book_rental.utils.validation.gender;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {GenderValidation.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {
    String message() default "Invalid gender value. Allowed values are: Male, Female, Other";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
