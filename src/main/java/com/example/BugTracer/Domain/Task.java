package com.example.BugTracer.Domain;

import jakarta.persistence.Entity;

@Entity
public class Task {

  private TaskId id;
  private String title;
  private String description;
  
  //TODO: add status enum


}
