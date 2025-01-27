package dar316.touve.book_rental.service;

import dar316.touve.book_rental.mapper.PublisherMapper;
import dar316.touve.book_rental.model.Publisher;
import dar316.touve.book_rental.model.dto.PublisherDto;
import dar316.touve.book_rental.repository.PublisherRepository;
import dar316.touve.book_rental.repository.projections.publisher.PublisherNameView;
import dar316.touve.book_rental.repository.projections.publisher.PublisherNameYearView;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

	private final PublisherRepository publisherRepository;
	private final PublisherMapper publisherMapper;

	public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
		this.publisherRepository = publisherRepository;
		this.publisherMapper = publisherMapper;
	}

	public Page<Publisher> getAllPublishers(int page, int size, String[] sort) {
		return publisherRepository.findAll(createPageable(page, size, sort));
	}

	@Caching(evict = {
			@CacheEvict(value = "publisherAllNames", allEntries = true),
			@CacheEvict(value = "publisherAllNamesYear", allEntries = true)
	})
	public Publisher addPublisher(PublisherDto publisherDto) {
		var publisher = publisherMapper.toEntity(publisherDto);

		return publisherRepository.save(publisher);
	}

	public PublisherDto getPublisherDto(Publisher publisher) {
		return publisherMapper.toDto(publisher);
	}

	private PageRequest createPageable(int page, int size, String[] sort) {
		return PageRequest.of(page, size, Sort.by(
                new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0])
				));
	}

	@Cacheable(value = "publisherAllNames")
	public Page<PublisherNameView> getAllNames() {
		return publisherRepository.findAllBy(PageRequest.of(0, 10));
	}

	@Cacheable(value = "publisherAllNamesYears")
	public Page<PublisherNameYearView> getAllNameYears() {
		return publisherRepository.findAllByOrderByEstablishedYearDesc(PageRequest.of(0, 10));
	}
}
