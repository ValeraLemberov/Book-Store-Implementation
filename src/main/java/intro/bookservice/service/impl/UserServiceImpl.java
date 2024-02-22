package intro.bookservice.service.impl;

import intro.bookservice.dto.user.UserRegistrationRequestDto;
import intro.bookservice.dto.user.UserResponseDto;
import intro.bookservice.exception.RegistrationException;
import intro.bookservice.mapper.UserMapper;
import intro.bookservice.model.Role;
import intro.bookservice.model.User;
import intro.bookservice.repository.user.UserRepository;
import intro.bookservice.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }

        User user = userMapper.toModel(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(new Role(2L)));
        return userMapper.toDto(userRepository.save(user));
    }
}
