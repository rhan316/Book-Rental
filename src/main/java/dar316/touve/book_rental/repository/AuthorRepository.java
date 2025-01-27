package dar316.touve.book_rental.repository;

import dar316.touve.book_rental.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	Optional<Author> findByFullName(String fullName);
	Optional<Author> findByEmail(String email);
	<T> List<T> findBy(Class<T> type);
}
