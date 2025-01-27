package dar316.touve.book_rental.repository.specifications;

import dar316.touve.book_rental.model.Book;
import dar316.touve.book_rental.model.Publisher;
import org.springframework.data.jpa.domain.Specification;

public class PublisherSpec {

	public static Specification<Book> hasNameLike(String keyword) {
		return ((root, _, cb) -> cb.like(cb.lower(root.get("name")),
				"%" + keyword.toLowerCase() + "%"));
	}

	public Specification<Publisher> hasLogicalNameLike(String logicalName) {
		return (r, _, cb) -> cb.equal(cb.lower(r.get("name")), logicalName.toLowerCase());
	}
}
