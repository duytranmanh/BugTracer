package com.example.BugTracer.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class UserRoleProjectId {
  @ManyToOne
  private User user;
  @ManyToOne
  private Project project;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }
}
