package com.example.BugTracer.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CommentId {
  @ManyToOne()
  private User user;

  @ManyToOne
  private Task task;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Task getProject() {
    return task;
  }

  public void setProject(Task task) {
    this.task = task;
  }
}
