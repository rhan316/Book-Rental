package dar316.touve.book_rental.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dar316.touve.book_rental.utils.validation.gender.ValidGender;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class AuthorDto {

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must only contain letters")
    private final String firstName;

    @NotBlank
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must only contain letters")
    private final String lastName;

    @Min(10)
    @Max(130)
    private final int age;

    private final String fullName;

    @ValidGender
    private final String gender;

    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^.+@.+\\..+", message = "Invalid e-mail format"
    )
    private final String email;

    @NotBlank
    @Size(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
    private final String city;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
}
