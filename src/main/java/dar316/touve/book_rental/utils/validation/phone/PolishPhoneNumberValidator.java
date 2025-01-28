package dar316.touve.book_rental.utils.validation.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PolishPhoneNumberValidator implements ConstraintValidator<ValidPolishPhoneNumber, String> {

    private static final String POLISH_PHONE_REGEX = "^(\\+48)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}$";

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) return false;

        return phoneNumber.matches(POLISH_PHONE_REGEX);
    }
}
