package dar316.touve.book_rental.mapper;

public interface DtoMapper<T, V> {
    T toEntity(V dto);
    V toDto(T entity);
}
