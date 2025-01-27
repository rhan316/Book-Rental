package dar316.touve.book_rental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String title;

	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	@JsonBackReference
	private Publisher publisher;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;
}
