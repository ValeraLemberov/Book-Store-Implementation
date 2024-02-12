package intro.bookservice.repository.spec;

import intro.bookservice.dto.BookSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {

    Specification<T> build(BookSearchParameters searchParameters);
}
