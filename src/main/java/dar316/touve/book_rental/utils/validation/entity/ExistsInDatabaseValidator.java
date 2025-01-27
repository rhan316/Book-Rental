package dar316.touve.book_rental.utils.validation.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsInDatabaseValidator implements ConstraintValidator<ExistsInDatabase, String> {

    @PersistenceContext
    private EntityManager em;

    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(ExistsInDatabase constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        String query = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE e." + fieldName + " = :value";
        long count = (long) em.createQuery(query)
                .setParameter("value", value)
                .getSingleResult();

        return count > 0;
    }
}
