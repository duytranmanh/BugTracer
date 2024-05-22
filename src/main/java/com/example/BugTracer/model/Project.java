package com.example.BugTracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
public class Project {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull
  @Length(max = 20, message = "your project name is too long")
  private String name;
  private LocalDateTime createdDate;
  private LocalDateTime lastUpdated;

  @PrePersist
  public void setUp() {
    createdDate = LocalDateTime.now();
  }

  @PreUpdate
  public void updatedAt() {lastUpdated = LocalDateTime.now();}


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
