package com.example.bachelorsbackend.controllers.handlers;

import com.example.bachelorsbackend.services.exceptions.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleServiceAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have access to this resource");
    }
}
