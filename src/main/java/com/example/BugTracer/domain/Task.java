package com.example.BugTracer.domain;

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
  private Status status;
  
  //TODO: add status enum


}
