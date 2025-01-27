package dar316.touve.book_rental.utils.validation.gender;


import dar316.touve.book_rental.model.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GenderValidation implements ConstraintValidator<ValidGender, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        return Arrays.stream(Gender.values())
                .anyMatch(g -> g.name().equalsIgnoreCase(value));
    }
}
