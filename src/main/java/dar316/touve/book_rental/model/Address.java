package dar316.touve.book_rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Street cannot be empty")
    @Size(max = 100, message = "Street name is too long")
    private String street;

    @NotBlank(message = "Building number cannot be empty")
    @Size(max = 10, message = "Building number is too long")
    private String buildingNumber;

    @Size(max = 10, message = "Apartment number is too long")
    private String apartmentNumber;

    @NotBlank(message = "City cannot be empty")
    @Size(max = 50, message = "City name is too long")
    private String city;

    @NotBlank(message = "Postal code cannot be empty")
    @Pattern(
            regexp = "\\d{2}-\\d{3}",
            message = "Postal code must follow the format XX-XXX"
    )
    private String postalCode;

    @Size(max = 50, message = "State name is too long")
    private String state;

    @NotBlank(message = "Country cannot be empty")
    @Size(max = 50, message = "Country name is too long")
    private String country;
}
