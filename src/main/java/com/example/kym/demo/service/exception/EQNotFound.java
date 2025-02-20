package com.example.kym.demo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EQNotFound extends RuntimeException {
    public EQNotFound(String message) {
        super(message);
    }
}
