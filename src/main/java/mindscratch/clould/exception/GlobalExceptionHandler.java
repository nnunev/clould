package mindscratch.clould.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VMNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleVMNotFound(VMNotFoundException ex) {

        return Map.of(
                "error", ex.getMessage()
        );
    }
}
