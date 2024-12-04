package com.aes.eventmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcepiton extends RuntimeException {

    public ResourceNotFoundExcepiton(String resourceName, String fieldName, String fieldValue) {
        super("%s not found with the given input data %s : '%s'".formatted(resourceName, fieldName, fieldValue));
    }
}
