package dar316.touve.book_rental.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {

	@NotBlank(message = "Publisher's name cannot be blank")
	@Size(min = 2, max = 50, message = "Publisher's name must be between 2 and 50 characters")
	private String name;

	@Max(2025)
	@Min(1950)
	private int establishedYear;
}
