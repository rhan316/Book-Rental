package dar316.touve.book_rental.repository.projections.book;

import dar316.touve.book_rental.repository.projections.publisher.PublisherNameView;

public interface TitlePublisherNameView {
    String getTitle();
    PublisherNameView getPublisher();
}
