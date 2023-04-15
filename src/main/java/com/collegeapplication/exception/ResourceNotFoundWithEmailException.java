package com.collegeapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundWithEmailException extends RuntimeException {
    public ResourceNotFoundWithEmailException(String email) {
        super("Resource with email " + email + " not found.");
    }
}

