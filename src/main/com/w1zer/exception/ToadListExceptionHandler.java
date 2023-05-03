package main.com.w1zer.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ToadListExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ToadListResponse> handleNotFoundException(NotFoundException e) {
        ToadListResponse response = new ToadListResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProfileAlreadyExistsException.class)
    public ResponseEntity<ToadListResponse> handleProfileAlreadyExistsException(ProfileAlreadyExistsException e) {
        ToadListResponse response = new ToadListResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ToadListResponse> handleProfileAlreadyExistsException(InvalidFormatException e) {
        String message = "Failed to parse some fields";
        ToadListResponse response = new ToadListResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

