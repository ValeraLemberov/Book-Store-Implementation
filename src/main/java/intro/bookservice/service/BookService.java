package intro.bookservice.service;

import intro.bookservice.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
