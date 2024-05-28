package com.example.BugTracer.controller;

import com.example.BugTracer.dto.ResponseExceptionMessage;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * This class handles exceptions thrown in controllers
 */
@RestControllerAdvice
public class RestResponseEntityExceptionHandler{

  /**
   * catch EntityExistException and return a bad request http status
   * @return bad request http status
   */
  @ExceptionHandler(EntityExistsException.class)
  protected ResponseEntity<ResponseExceptionMessage> handlingEntityExistException(EntityExistsException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseExceptionMessage(e.getMessage() +
        " already exist"));
  }

  /**
   * catch EntityNotFound and return a not found http status
   *
   * @return not found http status
   */
  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<ResponseExceptionMessage> handlingEntityNotFoundException(EntityNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ResponseExceptionMessage(e.getMessage() + " can not be found"));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ResponseExceptionMessage> handlingMethodArgumentNotValidException (
      MethodArgumentNotValidException e) {
    String errorMessage = "";
    List<FieldError> fieldErrorList = e.getFieldErrors();
    for (FieldError error: fieldErrorList) {
      errorMessage += error.getDefaultMessage() + " ";
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ResponseExceptionMessage(errorMessage));
  }

}
