package intro.bookservice.controller;

import intro.bookservice.dto.BookDto;
import intro.bookservice.dto.CreateBookRequestDto;
import intro.bookservice.mapper.BookMapper;
import intro.bookservice.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    List<BookDto> getAll() {
        return bookService.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    BookDto getBookById(@PathVariable Long id) {
        return bookMapper.toDto(bookService.findById(id));
    }

    @PostMapping
    BookDto createBook(@RequestBody CreateBookRequestDto bookDto) {
        return bookMapper.toDto(bookService.save(bookMapper.toModel(bookDto)));
    }
}
