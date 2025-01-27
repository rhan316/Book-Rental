package dar316.touve.book_rental.service;

import dar316.touve.book_rental.mapper.AuthorMapper;
import dar316.touve.book_rental.model.Author;
import dar316.touve.book_rental.model.dto.AuthorDto;
import dar316.touve.book_rental.repository.AuthorRepository;
import dar316.touve.book_rental.repository.projections.author.FullNameView;
import dar316.touve.book_rental.repository.projections.author.FullNameAndEmailView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    // TODO: Dodać id jako UUID, zaimplementować Cache (Redis), dodać różne Specifications oraz dodać Sort/Pageable, Sesja, Ciasteczka

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author addAuthor(AuthorDto authorDto) {
        if (authorRepository.findByEmail(authorDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Author with email already exists");
        }

        var author = authorMapper.toEntity(authorDto);
        authorRepository.save(author);

        return author;
    }

    public List<?> getAuthors(String projectionType) {
        return switch (projectionType) {
            case "fullName" -> authorRepository.findBy(FullNameView.class);
            case "fullNameEmail" -> authorRepository.findBy(FullNameAndEmailView.class);
            default -> throw new IllegalArgumentException("Invalid projection type");
        };
    }

    public AuthorDto getAuthorDto(Author author) {
        return authorMapper.toDto(author);
    }
}
