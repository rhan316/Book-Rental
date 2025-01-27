package dar316.touve.book_rental.repository.specifications;

import dar316.touve.book_rental.model.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpec {

	public static Specification<Author> hasFullNameAndAge(String fullName, int age) {
		return (r, _, cb) -> cb.and(
				cb.equal(cb.lower(r.get("fullName")), fullName.toLowerCase()),
				cb.equal(r.get("age"), age)
		);
	}
}
