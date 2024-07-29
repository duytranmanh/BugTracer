package com.example.BugTracer.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ProjectDTO {
  private Integer id;
  @NotNull(message = "name cannot be null")
  private String name;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
