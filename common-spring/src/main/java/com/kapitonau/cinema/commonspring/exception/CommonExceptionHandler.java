package com.kapitonau.cinema.commonspring.exception;

import com.kapitonau.cinema.commonspring.dto.CommonExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({CommonException.class})
    public ResponseEntity<CommonExceptionResponse> commonExceptionHandler(CommonException commonException) {
        return ResponseEntity.status(commonException.getHttpStatus()).body(commonException.getResponse());
    }

}
