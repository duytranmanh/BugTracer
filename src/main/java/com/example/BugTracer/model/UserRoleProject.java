package com.example.BugTracer.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class UserRoleProject {
  @EmbeddedId
  private UserRoleProjectId id;

  @Enumerated(EnumType.ORDINAL)
  private UserRole userRole;

  public UserRoleProjectId getId() {
    return id;
  }

  public void setId(UserRoleProjectId id) {
    this.id = id;
  }

  public UserRole getRole() {
    return userRole;
  }

  public void setRole(UserRole userRole) {
    this.userRole = userRole;
  }
}
