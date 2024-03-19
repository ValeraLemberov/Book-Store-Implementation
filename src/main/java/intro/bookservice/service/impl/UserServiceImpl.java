package intro.bookservice.service.impl;

import intro.bookservice.dto.user.UserRegistrationRequestDto;
import intro.bookservice.dto.user.UserResponseDto;
import intro.bookservice.exception.RegistrationException;
import intro.bookservice.mapper.UserMapper;
import intro.bookservice.model.ShoppingCart;
import intro.bookservice.model.User;
import intro.bookservice.repository.cart.ShoppingCartRepository;
import intro.bookservice.repository.role.RoleRepository;
import intro.bookservice.repository.user.UserRepository;
import intro.bookservice.service.UserService;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final Long USER_ROLE_ID = 2L;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }

        User user = userMapper.toModel(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleRepository.findById(USER_ROLE_ID)
                .orElseThrow(() -> new NoSuchElementException("Can't find a role by index: "
                        + USER_ROLE_ID))));
        User savedUser = userRepository.save(user);
        shoppingCartRepository.save(new ShoppingCart(savedUser));
        return userMapper.toDto(savedUser);
    }
}
