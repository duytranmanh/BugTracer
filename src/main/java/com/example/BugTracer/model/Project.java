package com.example.BugTracer.model;

import com.example.BugTracer.dto.UserProjectDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Project {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull
  @Length(max = 20, message = "your project name is too long")
  private String name;
  private LocalDateTime createdDate;
  private LocalDateTime lastUpdated;

  //TODO: fix cascade
  @OneToMany
  @Cascade(CascadeType.REMOVE)
  private List<UserProject> userProjectList;

  @PrePersist
  public void setUp() {
    createdDate = LocalDateTime.now();
  }

  @PreUpdate
  public void updatedAt() {lastUpdated = LocalDateTime.now();}

  public void setId(Integer id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }
}
