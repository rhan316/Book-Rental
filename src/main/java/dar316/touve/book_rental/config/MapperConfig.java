package dar316.touve.book_rental.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

	@Bean
	public ObjectMapper objectMapper() {

		var mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
		mapper.registerModule(new Jdk8Module());
		mapper.registerModule(new JavaTimeModule());

		return mapper;
	}
}
