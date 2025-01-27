package dar316.touve.book_rental;

import dar316.touve.book_rental.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@Autowired
	private CacheManager cacheManager;

	@Test
	void testCacheForGetAllBooks() {

		var booksFirstCall = bookService.getAllBooks();
		assertThat(booksFirstCall).isNotEmpty();

		var cache = cacheManager.getCache("allBooks");
		assertThat(cache).isNotNull();

		var cacheBooks = cache.get("allBooks", List.class);
		assertThat(cacheBooks).isEqualTo(booksFirstCall);
	}
}
