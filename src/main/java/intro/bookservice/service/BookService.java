package intro.bookservice.service;

import intro.bookservice.dto.book.BookDto;
import intro.bookservice.dto.book.BookSearchParameters;
import intro.bookservice.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto update(Long id, CreateBookRequestDto bookDto);

    void delete(Long id);

    List<BookDto> search(BookSearchParameters searchParameters);
}
