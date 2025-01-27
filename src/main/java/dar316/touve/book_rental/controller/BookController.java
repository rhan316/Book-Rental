package dar316.touve.book_rental.controller;

import dar316.touve.book_rental.model.Book;
import dar316.touve.book_rental.model.dto.BookDto;
import dar316.touve.book_rental.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/all")
	public List<BookDto> getAllBooks() {
		return bookService.getAllBooks();
	}

	// s => search a => author
	@GetMapping("/s")
	public List<Book> getBooksByAuthor(@RequestParam String a) {
		return bookService.getBooksByAuthor(a);
	}

	// k => keyword
	@GetMapping("/k")
	public List<Book> searchBooksByTitleKeyword(@RequestParam String t) {
		return bookService.searchTittleByKeyword(t);
	}

	@PostMapping("/add")
	public Optional<Book> addBook(@RequestBody BookDto bookDto) {
		return bookService.saveBook(bookDto);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable long id) {
		bookService.deleteById(id);
	}
}
