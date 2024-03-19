package intro.bookservice.controller;

import intro.bookservice.dto.user.UserLoginRequestDto;
import intro.bookservice.dto.user.UserLoginResponseDto;
import intro.bookservice.dto.user.UserRegistrationRequestDto;
import intro.bookservice.dto.user.UserResponseDto;
import intro.bookservice.exception.RegistrationException;
import intro.bookservice.security.AuthenticationService;
import intro.bookservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Endpoints authentication.")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "endpoint for registration",
            description = "Users can register using email addresses, password, etc.")
    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRegistrationRequestDto userDto)
            throws RegistrationException {
        return userService.register(userDto);
    }

    @Operation(summary = "endpoint for login registered user",
            description = "After login, a user can use the Book service using a token.")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
