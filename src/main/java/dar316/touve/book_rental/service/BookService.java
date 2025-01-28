package dar316.touve.book_rental.service;

import dar316.touve.book_rental.model.Book;
import dar316.touve.book_rental.model.dto.BookDto;
import dar316.touve.book_rental.repository.AuthorRepository;
import dar316.touve.book_rental.repository.BookRepository;
import dar316.touve.book_rental.repository.PublisherRepository;
import dar316.touve.book_rental.repository.projections.book.TitleView;
import dar316.touve.book_rental.repository.specifications.BookSpec;
import dar316.touve.book_rental.utils.NativeSqlUtils;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	private final AuthorRepository authorRepository;
	private final BookSpec spec;
	private final NativeSqlUtils nativeSqlUtils;

	public BookService(
			BookRepository bookRepository,
			PublisherRepository publisherRepository,
			AuthorRepository authorRepository,
			BookSpec spec,
			NativeSqlUtils nativeSqlUtils
	) {
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
		this.authorRepository = authorRepository;
		this.spec = spec;
		this.nativeSqlUtils = nativeSqlUtils;
	}

	private List<BookDto> fetchAllBooks() {
		return fetchAllBooksStream()
				.map(book -> new BookDto(
						book.getTitle(),
						book.getIsbn(),
						book.getAuthor().getFullName(),
						book.getPublisher().getName(),
						book.getReleaseDate()
				)).collect(Collectors.toList());
	}

	private Stream<Book> fetchAllBooksStream() {
		long bookCount = bookRepository.count();

		return bookCount > 1_000_00 ?
				bookRepository.findAll().parallelStream() :
				bookRepository.findAll().stream();
	}

	@Cacheable(value = "allBooks")
	public List<BookDto> getAllBooks() {
		return fetchAllBooks();
	}

	public String getBookTitleById(long id) {
		return bookRepository.findById(id, TitleView.class).getTitle();
	}

	public List<Book> getBooksByAuthor(String author) {
		var specBook = spec.hasTitleLike(author);
		return bookRepository.findAll(specBook);
	}

	public List<Book> searchTittleByKeyword(String keyword) {
		var s = spec.hasTitleLike(keyword);
		return bookRepository.findAll(s);
	}

	@CacheEvict(value = "allBooks", allEntries = true)
	public Optional<Book> saveBook(BookDto bookDto) {
		var publisher = publisherRepository.findByName(bookDto.getPublisherName())
				.orElseThrow(() -> new RuntimeException("Publisher not found!"));

		var author = authorRepository.findByFullName(bookDto.getAuthor())
				.orElseThrow(() -> new RuntimeException("Author not found!"));

		var book = Book.builder()
				.title(bookDto.getTitle())
				.isbn(bookDto.getIsbn())
				.author(author)
				.publisher(publisher)
				.releaseDate(bookDto.getReleaseDate())
				.build();

		return Optional.of(bookRepository.save(book));
	}

	@Transactional
	public void deleteAll() {
		bookRepository.deleteAll();
		nativeSqlUtils.resetId("Book");
	}

	public void deleteById(long id) {
		bookRepository.deleteById(id);
	}

	public Book getBookById(long id) {
		return bookRepository.findById(id);
	}
}
