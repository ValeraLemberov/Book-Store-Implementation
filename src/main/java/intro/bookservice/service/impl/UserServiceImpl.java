package intro.bookservice.service.impl;

import intro.bookservice.dto.user.UserRegistrationRequestDto;
import intro.bookservice.dto.user.UserResponseDto;
import intro.bookservice.mapper.UserMapper;
import intro.bookservice.repository.user.UserRepository;
import intro.bookservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userDto) {
        return userMapper
                .toDto(userRepository
                        .save(userMapper
                                .toModel(userDto)));
    }
}
