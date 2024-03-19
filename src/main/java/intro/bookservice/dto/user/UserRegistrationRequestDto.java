package intro.bookservice.dto.user;

import intro.bookservice.annotation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(password = "password", repeatPassword = "repeatPassword")
public class UserRegistrationRequestDto {
    @Email
    @NotEmpty
    private String email;
    @Size(min = 6, max = 255)
    private String password;
    @Size(min = 6, max = 255)
    private String repeatPassword;
    @NotEmpty(message = "First name must not be blank.")
    @Size(max = 255, message = "First name length must be less then 255 characters.")
    private String firstName;
    @NotEmpty(message = "Last name name must not be blank.")
    @Size(max = 255, message = "Last name length must be less then 255 characters.")
    private String lastName;
    private String shippingAddress;
}
