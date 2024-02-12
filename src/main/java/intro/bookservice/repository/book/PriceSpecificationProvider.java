package intro.bookservice.repository.book;

import intro.bookservice.model.Book;
import intro.bookservice.repository.spec.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    private static final String PRICE = "price";

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(PRICE)
                .in(Arrays.stream(params).toArray());
    }

    @Override
    public String getKey() {
        return PRICE;
    }
}
