package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementDataNotFoundException extends RuntimeException {
    public ElementDataNotFoundException(String message) {
        super(message);
    }
}
