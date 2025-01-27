package dar316.touve.book_rental.mapper;

import dar316.touve.book_rental.model.Publisher;
import dar316.touve.book_rental.model.dto.PublisherDto;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper implements DtoMapper<Publisher, PublisherDto> {

    @Override
    public Publisher toEntity(PublisherDto dto) {
        return Publisher.builder()
                .name(dto.getName())
                .establishedYear(dto.getEstablishedYear())
                .build();
    }

    @Override
    public PublisherDto toDto(Publisher entity) {
        return PublisherDto.builder()
                .name(entity.getName())
                .establishedYear(entity.getEstablishedYear())
                .build();
    }
}
