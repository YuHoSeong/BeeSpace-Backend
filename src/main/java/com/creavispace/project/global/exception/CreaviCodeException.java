package com.creavispace.project.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreaviCodeException extends RuntimeException{
    private GlobalErrorCode errorCode;
}
