package com.example.BugTracer.model;

import com.example.BugTracer.dto.UserProjectDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull
  @Length(max = 20, message = "your project name is too long")
  private String name;
  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastUpdated;

  @OneToMany(mappedBy = "id.project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserProject> userProjectList;

  @OneToMany(mappedBy = "id.project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Task> taskList;

  public List<UserProject> getUserProjectList() {
    return userProjectList;
  }

  public void setUserProjectList(List<UserProject> userProjectList) {
    this.userProjectList = userProjectList;
  }

  public List<Task> getTaskList() {
    return taskList;
  }

  public void setTaskList(List<Task> taskList) {
    this.taskList = taskList;
  }



  public void setId(Integer id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }
}
