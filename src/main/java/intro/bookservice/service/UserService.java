package intro.bookservice.service;

import intro.bookservice.dto.user.UserRegistrationRequestDto;
import intro.bookservice.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto userDto);
}
