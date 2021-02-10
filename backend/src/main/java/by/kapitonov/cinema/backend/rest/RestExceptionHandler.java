package by.kapitonov.cinema.backend.rest;

import by.kapitonov.cinema.backend.exception.ModelAlreadyExistsException;
import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.exception.ApiErrorStatus;
import by.kapitonov.cinema.backend.rest.response.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ModelNotFoundException.class})
    public ResponseEntity<ApiExceptionResponse> modelNotFoundException(ModelNotFoundException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getMessage(),
                ApiErrorStatus.NOT_FOUNT,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ModelAlreadyExistsException.class})
    public ResponseEntity<ApiExceptionResponse> modelAlreadyExistsException(ModelAlreadyExistsException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getMessage(),
                ApiErrorStatus.ALREADY_EXISTS,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

}
