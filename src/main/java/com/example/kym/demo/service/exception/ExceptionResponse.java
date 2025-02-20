package com.example.kym.demo.service.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
        LocalDateTime time,
        String message,
        String detail
) {
}
