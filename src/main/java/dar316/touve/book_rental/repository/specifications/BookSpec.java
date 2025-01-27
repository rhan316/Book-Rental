package dar316.touve.book_rental.repository.specifications;

import dar316.touve.book_rental.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpec {

	public Specification<Book> hasTitleLike(String keyword) {
		return ((root, _, cb) -> cb.like(cb.lower(root.get("title")),
				"%" + keyword.toLowerCase() + "%"));
	}
}
