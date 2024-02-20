package intro.bookservice.controller;

import intro.bookservice.dto.user.UserRegistrationRequestDto;
import intro.bookservice.dto.user.UserResponseDto;
import intro.bookservice.exception.RegistrationException;

import intro.bookservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto register(@Valid @RequestBody UserRegistrationRequestDto userDto)
            throws RegistrationException {
        return userService.register(userDto);
    }
}
