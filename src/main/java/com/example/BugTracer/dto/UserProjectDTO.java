package com.example.BugTracer.dto;

import com.example.BugTracer.model.Role;
import com.example.BugTracer.model.UserProjectId;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * DTO for UserRoleProject
 */
public class UserProjectDTO {
  @NotNull
  private Integer userId;
  @NotNull
  private Integer projectId;
  @NotNull
  private Role role;
  private LocalDateTime createdDate;
  private LocalDateTime lastUpdated;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getProjectId() {
    return projectId;
  }

  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }
}
