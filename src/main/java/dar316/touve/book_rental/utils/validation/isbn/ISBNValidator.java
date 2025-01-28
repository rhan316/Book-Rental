package dar316.touve.book_rental.utils.validation.isbn;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ValidISBN, String> {


    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        if (isbn == null || isbn.isEmpty()) return false;

        isbn = isbn.replaceAll("-", "");

        if (isbn.length() == 10) {
            return isValidISBN10(isbn);
        } else if (isbn.length() == 13) {
            return isValidISBN13(isbn);
        }

        return false;
    }

    private boolean isValidISBN10(String isbn) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (!Character.isDigit(isbn.charAt(i))) return false;
            sum += (isbn.charAt(i) - '0') * (10 - i);
        }

        char checksum = isbn.charAt(9);
        sum += (checksum == 'X') ? 10 : (Character.isDigit(checksum) ? checksum - '0' : 0);

        return sum % 11 == 0;
    }

    private boolean isValidISBN13(String isbn) {
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            if (!Character.isDigit(isbn.charAt(i))) return false;
            int digit = isbn.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        return sum % 10 == 0;
    }
}
