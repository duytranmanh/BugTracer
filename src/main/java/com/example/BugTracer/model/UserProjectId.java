package com.example.BugTracer.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Objects;

@Embeddable
public class UserProjectId {
  @ManyToOne
  private User user;
  @ManyToOne
  private Project project;

  public UserProjectId(User user, Project project) {
    this.user = user;
    this.project = project;
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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserProjectId that = (UserProjectId) o;
    return Objects.equals(user, that.user) && Objects.equals(project, that.project);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, project);
  }
}
