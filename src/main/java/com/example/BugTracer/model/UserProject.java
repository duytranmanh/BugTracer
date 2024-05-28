package com.example.BugTracer.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

/**
 * This table contains user-project relationship (user role in a project)
 */
@Entity
public class UserProject {
  @EmbeddedId
  private UserProjectId id;
  @Enumerated(EnumType.ORDINAL)
  private Role role;
  private LocalDateTime createdDate;
  private LocalDateTime lastUpdated;

  public void setId(UserProjectId id) {
    this.id = id;
  }

  public UserProjectId getId() {
    return id;
  }

  @PrePersist
  public void setUp() {
    createdDate = LocalDateTime.now();
  }

  @PreUpdate
  public void updatedAt() {
    lastUpdated = LocalDateTime.now();
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }
}
