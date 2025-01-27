package dar316.touve.book_rental.controller;

import dar316.touve.book_rental.model.Publisher;
import dar316.touve.book_rental.model.dto.PublisherDto;
import dar316.touve.book_rental.repository.projections.publisher.PublisherNameView;
import dar316.touve.book_rental.repository.projections.publisher.PublisherNameYearView;
import dar316.touve.book_rental.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pub")
public class PublisherController {

	private final PublisherService publisherService;

	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@GetMapping("/all")
	public Page<Publisher> getAllPublishers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "name,asc") String[] sort
	) {
		return publisherService.getAllPublishers(page, size, sort);
	}

	@GetMapping("/names")
	public Page<PublisherNameView> getAllNames() {
		return publisherService.getAllNames();
	}

	@GetMapping("/names-year")
	public Page<PublisherNameYearView> getAllNameYears() {
		return publisherService.getAllNameYears();
	}

	@PostMapping("/add")
	public PublisherDto addPublisher(@Valid @RequestBody PublisherDto publisherDto) {
		var savedPublisher = publisherService.addPublisher(publisherDto);

		return publisherService.getPublisherDto(savedPublisher);
	}
}
