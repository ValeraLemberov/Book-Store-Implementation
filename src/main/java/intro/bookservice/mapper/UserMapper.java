package intro.bookservice.mapper;

import intro.bookservice.config.MapperConfig;
import intro.bookservice.dto.user.UserRegistrationRequestDto;
import intro.bookservice.dto.user.UserResponseDto;
import intro.bookservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toModel(UserRegistrationRequestDto userDto);

    UserResponseDto toDto(User user);
}
