package dar316.touve.book_rental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootApplication
public class BookRentalApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(BookRentalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookRentalApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		var polishFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH);
		var zoneNow = ZonedDateTime.now();
		log.info("{}", zoneNow);

		var someDate = LocalDate.of(2025, Month.DECEMBER, 3);
		log.info("Year: {}, Month: {}, Day: {}", someDate.getYear(), someDate.getMonthValue(), someDate.getDayOfMonth());

		var customPattern = DateTimeFormatter.ofPattern("'Data:' dd MMMM yyyy 'Godzina:' hh:mm");
		var someTime = LocalTime.of(14, 31);

		log.info("{}", LocalDateTime.of(someDate, someTime).format(customPattern));
	}
}
