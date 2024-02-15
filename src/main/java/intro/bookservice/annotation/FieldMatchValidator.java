package intro.bookservice.annotation;

import intro.bookservice.exception.RegistrationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        password = constraintAnnotation.password();
        repeatPassword = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String firstPassword = (String) new BeanWrapperImpl(value).getPropertyValue(password);
        String secondPassword = (String) new BeanWrapperImpl(value).getPropertyValue(repeatPassword);

        boolean isMatch = firstPassword != null && firstPassword.equals(secondPassword);
        if (!isMatch) {
            throw new RegistrationException("Passwords isn't match");
        }
        return true;
    }
}
