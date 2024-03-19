package intro.bookservice.mapper;

import intro.bookservice.config.MapperConfig;
import intro.bookservice.dto.category.CategoryRequestDto;
import intro.bookservice.dto.category.CategoryResponseDto;
import intro.bookservice.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

    Category toModel(CategoryRequestDto categoryRequestDto);
}
