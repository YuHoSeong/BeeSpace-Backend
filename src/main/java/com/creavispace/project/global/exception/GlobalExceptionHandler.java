package com.creavispace.project.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.creavispace.project.domain.common.dto.FailResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CreaviCodeException.class)
    public ResponseEntity<FailResponseDto> handleNotFoundException(CreaviCodeException ex) {
        GlobalErrorCode globalErrorCode = ex.getErrorCode();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, globalErrorCode.getMessage(), globalErrorCode.getCode()));
    }
}
