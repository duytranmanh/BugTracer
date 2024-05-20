package com.example.BugTracer.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class UserRoleProject {
  @EmbeddedId
  private UserRoleProjectId id;

  @Enumerated(EnumType.ORDINAL)
  private Role role;

  public UserRoleProjectId getId() {
    return id;
  }

  public void setId(UserRoleProjectId id) {
    this.id = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
