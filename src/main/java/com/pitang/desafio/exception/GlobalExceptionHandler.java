package com.pitang.desafio.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponse("Unauthorized", ex.getMessage());
    }

    @ExceptionHandler({io.jsonwebtoken.ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Unauthorized - invalid session", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({io.jsonwebtoken.JwtException.class})
    public ResponseEntity<Object> handleJwtException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CarLicensePlateAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCarLicensePlateAlreadyExistsException(CarLicensePlateAlreadyExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MissingFieldsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingFieldsException(MissingFieldsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidFieldsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidFieldsException(InvalidFieldsException e) {
        return e.getMessage();
    }
}