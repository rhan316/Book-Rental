package dar316.touve.book_rental.model.dto;

import dar316.touve.book_rental.model.Author;
import dar316.touve.book_rental.model.Publisher;
import dar316.touve.book_rental.utils.validation.entity.ExistsInDatabase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

	@NotBlank(message = "Title cannot be blank")
	@Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
	private String title;

	@ExistsInDatabase(
			entityClass = Author.class,
			fieldName = "email",
			message = "This author does not exists"
	)
	private String author;

	@ExistsInDatabase(
			entityClass = Publisher.class,
			fieldName = "name",
			message = "This publisher does not exists"
	)
	private String publisherName;
}
