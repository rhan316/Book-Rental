package dar316.touve.book_rental.controller;

import dar316.touve.book_rental.model.Author;
import dar316.touve.book_rental.model.dto.AuthorDto;
import dar316.touve.book_rental.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/au")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/add")
    public AuthorDto getAuthorById(@Valid @RequestBody AuthorDto authorDto) {
        var savedAuthor = authorService.addAuthor(authorDto);

        return authorService.getAuthorDto(savedAuthor);
    }

    @GetMapping("/")
    public List<?> getAuthorByProjection(@RequestParam String pro) {
        return authorService.getAuthors(pro);
    }
}
