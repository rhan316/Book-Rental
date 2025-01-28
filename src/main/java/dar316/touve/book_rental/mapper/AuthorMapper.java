package dar316.touve.book_rental.mapper;

import dar316.touve.book_rental.model.Author;
import dar316.touve.book_rental.model.Gender;
import dar316.touve.book_rental.model.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements DtoMapper<Author, AuthorDto> {

    @Override
    public Author toEntity(AuthorDto dto) {
        return Author.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .gender(Gender.valueOf(dto.getGender()))
                .email(dto.getEmail())
                .address(dto.getAddress())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    @Override
    public AuthorDto toDto(Author entity) {
        return AuthorDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .fullName(entity.getFullName())
                .age(entity.getAge())
                .gender(String.valueOf(entity.getGender()))
                .email(entity.getEmail())
                .address(entity.getAddress())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
