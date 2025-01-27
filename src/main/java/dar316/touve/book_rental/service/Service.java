package dar316.touve.book_rental.service;

public interface Service<T, V> {
    T toEntity(V value);
    V toDto(T entity);
}
