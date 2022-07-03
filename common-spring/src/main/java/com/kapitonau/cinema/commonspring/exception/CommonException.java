package com.kapitonau.cinema.commonspring.exception;

import com.kapitonau.cinema.commonspring.dto.CommonExceptionResponse;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private int httpStatus;
    private CommonExceptionResponse response;

    public CommonException(int httpStatus, String code, String message, String devMessage, String userMessage) {
        super(message);
        this.httpStatus = httpStatus;
        this.response = new CommonExceptionResponse(code, devMessage, userMessage);
    }

    public CommonException(String code, String message, String devMessage, String userMessage) {
        super(message);
        this.httpStatus = 400;
        this.response = new CommonExceptionResponse(code, devMessage, userMessage);
    }

    public CommonException(String message, CommonExceptionResponse response) {
        super(message);
        this.httpStatus = 400;
        this.response = response;
    }
}
