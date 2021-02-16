package by.kapitonov.cinema.fapi.exception;

import by.kapitonov.cinema.fapi.rest.response.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ModelNotFoundException.class})
    public ResponseEntity<ApiExceptionResponse> modelNotFoundException(ModelNotFoundException ex) {

        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getMessage(),
                ApiErrorStatus.NOT_FOUNT.name(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ModelAlreadyExistsException.class})
    public ResponseEntity<ApiExceptionResponse> modelAlreadyExistsException(ModelAlreadyExistsException ex) {

        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getMessage(),
                ApiErrorStatus.ALREADY_EXISTS.name(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidEmailOrPasswordException.class})
    public ResponseEntity<ApiExceptionResponse> emailOrPasswordException(InvalidEmailOrPasswordException ex) {

        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getMessage(),
                ApiErrorStatus.INVALID_CREDENTIAL.name(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidTokenException.class})
    public ResponseEntity<ApiExceptionResponse> invalidTokenException(InvalidTokenException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getMessage(),
                ApiErrorStatus.INVALID_TOKEN.name(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AccessException.class})
    public ResponseEntity<ApiExceptionResponse> accessException(AccessException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse(
                ex.getMessage(),
                ApiErrorStatus.ACCESS_DENIED.name(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
