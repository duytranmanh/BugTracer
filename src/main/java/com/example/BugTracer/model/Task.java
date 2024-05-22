package com.example.BugTracer.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Task {
  @EmbeddedId
  private TaskId id;
  private String title;
  private String description;

  @Enumerated(EnumType.ORDINAL)
  private TaskStatus taskStatus;

  public TaskId getId() {
    return id;
  }

  public void setId(TaskId id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    return taskStatus;
  }

  public void setStatus(TaskStatus taskStatus) {
    this.taskStatus = taskStatus;
  }
}
