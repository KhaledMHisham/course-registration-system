package com.example.demo.errorhandler;

import com.example.demo.dto.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationErrorHandler {

  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity<CustomError> handleCourseNotFoundException(CourseNotFoundException ex) {
    CustomError error = CustomError.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    CustomError error = CustomError.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(ex.getFieldError().getDefaultMessage())
        .build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<CustomError> handleRunTimeException(RuntimeException ex) {
    CustomError error = CustomError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Internal server error. See logs for details.")
        .build();
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
