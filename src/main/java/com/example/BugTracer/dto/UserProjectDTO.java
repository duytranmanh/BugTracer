package com.example.BugTracer.dto;

import com.example.BugTracer.model.Role;
import com.example.BugTracer.model.UserProjectId;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PathVariable;

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
  private LocalDateTime updatedDate;

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

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
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
