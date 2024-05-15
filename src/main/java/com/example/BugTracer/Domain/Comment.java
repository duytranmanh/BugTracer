package com.example.BugTracer.Domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Comment {
  @EmbeddedId
  private CommentId id;
  private String content;

  public CommentId getId() {
    return id;
  }

  public void setId(CommentId id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
