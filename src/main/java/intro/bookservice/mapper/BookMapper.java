package intro.bookservice.mapper;

import intro.bookservice.config.MapperConfig;
import intro.bookservice.dto.BookDto;
import intro.bookservice.dto.CreateBookRequestDto;
import intro.bookservice.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);
}
