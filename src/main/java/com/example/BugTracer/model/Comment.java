package com.example.BugTracer.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
