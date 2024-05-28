package com.example.BugTracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull(message = "username cannot be empty")
  @Length(min = 1, max = 20, message = "your username is too long")
  private String username;
  @NotNull(message = "username cannot be empty")
  @Length(min = 8, message = "your password is too short")
  private String password;
  @NotNull(message = "username cannot be empty")
  @Email
  private String email;
  @OneToMany
  @Cascade(value = CascadeType.REMOVE)
  private List<UserProject> userProjectList;
  private LocalDateTime createdDate;
  private LocalDateTime lastUpdated;
  @Enumerated(EnumType.ORDINAL)
  private Role role;

  @PrePersist
  public void setUp() {
    createdDate = LocalDateTime.now();
    role = Role.REGISTERED_USER;
  }

  public List<UserProject> getUserProjectList() {
    return userProjectList;
  }

  public void setUserProjectList(List<UserProject> userProjectList) {
    this.userProjectList = userProjectList;
  }

  @PreUpdate
  public void updatedAt() {
    lastUpdated = LocalDateTime.now();
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role userRole) {
    this.role = userRole;
  }


  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
