package com.Canhan.Car.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Báo cho Spring Boot biết khi Exception này được ném ra,
// hãy trả về mã lỗi 404 NOT FOUND
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
