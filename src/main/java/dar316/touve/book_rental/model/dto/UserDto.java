package dar316.touve.book_rental.model.dto;

import dar316.touve.book_rental.model.Address;
import dar316.touve.book_rental.utils.validation.phone.ValidPolishPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must only contain letters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must only contain letters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^.+@.+\\..+", message = "Invalid e-mail format")
    @Email
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @ValidPolishPhoneNumber
    private String phone;

    private Address address;
    private LocalDateTime creationDate;
}
