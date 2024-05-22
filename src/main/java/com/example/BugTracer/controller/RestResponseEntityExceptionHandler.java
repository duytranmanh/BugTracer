package com.example.BugTracer.controller;

import com.example.BugTracer.dto.ResponseExceptionMessage;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
  protected ResponseEntity<Object> handlingEntityExistException() {
    return ResponseEntity.badRequest().build();
  }

  /**
   * catch EntityNotFound and return a not found http status
   *
   * @return not found http status
   */
  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<ResponseExceptionMessage> handlingEntityNotFoundException() {
    System.out.println("exception 33");
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ResponseExceptionMessage("username existed"));
  }
//  @ExceptionHandler(EntityNotFoundException.class)
//  protected ResponseEntity<String> handlingEntityNotFoundException() {
//
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("username not found");
//  }

}
