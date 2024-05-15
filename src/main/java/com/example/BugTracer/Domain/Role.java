package com.example.BugTracer.Domain;

import jakarta.persistence.ManyToOne;

public class Role {
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
