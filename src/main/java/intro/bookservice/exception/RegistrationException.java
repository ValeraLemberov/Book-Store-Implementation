package intro.bookservice.exception;

public class RegistrationException extends RuntimeException{
    public RegistrationException(String message) {
        super(message);
    }
}