package intro.bookservice.service;

import intro.bookservice.dto.book.BookDtoWithoutCategoryIds;
import intro.bookservice.dto.book.BookResponseDto;
import intro.bookservice.dto.book.BookSearchParameters;
import intro.bookservice.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto bookDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findById(Long id);

    BookResponseDto update(Long id, CreateBookRequestDto bookDto);

    void delete(Long id);

    List<BookResponseDto> search(BookSearchParameters searchParameters);

    List<BookDtoWithoutCategoryIds> findAllBooksByCategoryId(Long categoryId);
}
