package dar316.touve.book_rental.utils;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class NativeSqlUtils {

	private final EntityManager entityManager;

	private NativeSqlUtils(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void resetId(String name) {
		entityManager.createNativeQuery("ALTER SEQUENCE " + name + "_id_seq RESTART WITH 1").executeUpdate();
	}
}
