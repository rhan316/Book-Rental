package dar316.touve.book_rental.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

public interface Spec<T> {
	Specification<T> hasLogicalName(String logicalName);
}
