package dar316.touve.book_rental.utils.validation.isbn;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Constraint(validatedBy = ISBNValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidISBN {
    String message() default "Invalid ISBN";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
