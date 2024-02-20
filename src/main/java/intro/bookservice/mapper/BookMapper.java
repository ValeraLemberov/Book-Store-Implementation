package intro.bookservice.mapper;

import intro.bookservice.config.MapperConfig;
import intro.bookservice.dto.book.BookDto;
import intro.bookservice.dto.book.CreateBookRequestDto;
import intro.bookservice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Book toModel(CreateBookRequestDto bookDto);
}
