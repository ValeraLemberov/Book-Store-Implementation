package intro.bookservice.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMsg)
                .toList();
        Map<String, Object> body = createBody(HttpStatus.BAD_REQUEST, errors.toString());
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFound(
            EntityNotFoundException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        String id = url.substring(url.lastIndexOf("/") + 1);
        Map<String, Object> body = createBody(HttpStatus.NOT_FOUND,
                "Entity not fount by id: " + id);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Object> handlerRegistrationException(
            RegistrationException ex,
            HttpServletRequest request) {
        Map<String, Object> body =
                createBody(HttpStatus.UNAUTHORIZED, "Passwords do not match");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    private String getErrorMsg(ObjectError e) {
        if (e instanceof FieldError) {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            return  field + " " + message;
        }
        return e.getDefaultMessage();
    }

    private Map<String, Object> createBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("message", message);
        return body;
    }
}
