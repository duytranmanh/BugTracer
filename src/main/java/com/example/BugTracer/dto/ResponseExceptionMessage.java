package com.example.BugTracer.dto;

public class ResponseExceptionMessage {
  private String message;

  public ResponseExceptionMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
