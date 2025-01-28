package dar316.touve.book_rental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookRentalApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(BookRentalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookRentalApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	}
}
