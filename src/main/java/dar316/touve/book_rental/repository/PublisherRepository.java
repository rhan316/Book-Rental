package dar316.touve.book_rental.repository;

import dar316.touve.book_rental.model.Publisher;
import dar316.touve.book_rental.repository.projections.publisher.PublisherNameView;
import dar316.touve.book_rental.repository.projections.publisher.PublisherNameYearView;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long>, JpaSpecificationExecutor<Publisher> {
	Optional<Publisher> findByName(String name);
	@NonNull
	Page<Publisher> findAll(@NonNull Pageable pageable);

	Page<PublisherNameView> findAllBy(Pageable pageable);
	Page<PublisherNameYearView> findAllByOrderByEstablishedYearDesc(Pageable pageable);
}
