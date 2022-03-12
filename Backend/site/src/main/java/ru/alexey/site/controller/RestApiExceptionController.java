package ru.alexey.site.controller;
/* 
08.03.2022: Alexey created this file inside the package: ru.alexey.site.controller 
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.alexey.site.exception.EntityAlreadyExistsException;
import ru.alexey.site.exception.EntityNotFoundException;
import ru.alexey.site.model.ApiError;

import java.util.Date;

@RestControllerAdvice
public class RestApiExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(EntityNotFoundException e) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        new Date()
                ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<ApiError> handleNotFound(EntityAlreadyExistsException e) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        new Date()
                ), HttpStatus.CONFLICT);
    }

}
