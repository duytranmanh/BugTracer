package com.example.BugTracer.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private String description;

  @ManyToOne
  private User author;
  @ManyToOne
  private Project project;

  @Enumerated(EnumType.ORDINAL)
  private TaskStatus taskStatus;

  @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> commentList;

  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedDate
  private LocalDateTime updatedDate;

  public TaskStatus getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(TaskStatus taskStatus) {
    this.taskStatus = taskStatus;
  }

  public List<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList(List<Comment> commentList) {
    this.commentList = commentList;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User user) {
    this.author = user;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TaskStatus getStatus() {
    if (taskStatus == null) taskStatus = TaskStatus.UNASSIGNED;
    return taskStatus;
  }

  public void setStatus(TaskStatus taskStatus) {
    this.taskStatus = taskStatus;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
}
