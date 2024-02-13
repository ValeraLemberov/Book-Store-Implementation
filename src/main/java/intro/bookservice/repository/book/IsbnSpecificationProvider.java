package intro.bookservice.repository.book;

import intro.bookservice.model.Book;
import intro.bookservice.repository.spec.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider implements SpecificationProvider<Book> {
    private static final String ISBN = "isbn";

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(ISBN).in(Arrays.stream(params).toArray());
    }

    @Override
    public String getKey() {
        return ISBN;
    }
}
