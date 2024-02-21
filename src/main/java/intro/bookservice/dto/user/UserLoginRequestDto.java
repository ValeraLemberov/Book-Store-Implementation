package intro.bookservice.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
    @NotEmpty
    @Email
    @Size(min = 6, max = 255)
    String email,
    @NotEmpty
    @Size(min = 6, max = 255)
    String password
) {
}

