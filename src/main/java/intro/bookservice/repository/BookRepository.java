package intro.bookservice.repository;

import intro.bookservice.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Book findById(Long id);
}
