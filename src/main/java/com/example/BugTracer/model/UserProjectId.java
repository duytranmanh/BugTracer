package com.example.BugTracer.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Embeddable
public class UserProjectId {
  @ManyToOne
  private User user;
  @ManyToOne
  private Project project;

  public UserProjectId(Integer userId, Integer projectId) {
    user = new User();
    user.setId(userId);

    project = new Project();
    project.setId(projectId);
  }

  public UserProjectId() {
  }

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
