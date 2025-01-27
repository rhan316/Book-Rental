package dar316.touve.book_rental.repository;

import dar316.touve.book_rental.model.Author;
import dar316.touve.book_rental.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	<T> T findById(long id, Class<T> type);
	<T> List<T> findAllBy(Class<T> type);

	List<Book> findByAuthor(Author author);
	void deleteById(long id);
	Book findById(long id);
}
