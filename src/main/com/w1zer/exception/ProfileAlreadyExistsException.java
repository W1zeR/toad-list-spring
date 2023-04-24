package main.com.w1zer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ProfileAlreadyExistsException extends RuntimeException {
    public ProfileAlreadyExistsException(final String msg) {
        super(msg);
    }
}
