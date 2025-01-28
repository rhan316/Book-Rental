package dar316.touve.book_rental.model.dto;

import dar316.touve.book_rental.model.Author;
import dar316.touve.book_rental.model.Publisher;
import dar316.touve.book_rental.utils.validation.entity.ExistsInDatabase;
import dar316.touve.book_rental.utils.validation.isbn.ValidISBN;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

	@NotBlank(message = "Title cannot be blank")
	@Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
	private String title;

	@NotBlank(message = "ISBN cannot be blank")
	@ValidISBN
	private String isbn;

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

	@NotNull(message = "Release date cannot be null")
	@Past(message = "Release date must be in the past")
	private LocalDate releaseDate;
}
