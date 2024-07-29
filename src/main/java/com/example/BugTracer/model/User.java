package com.example.BugTracer.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id=?")
@SQLRestriction(value = "is_deleted=false")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull(message = "username cannot be empty")
  @Length(max = 100, message = "your username is too long")
  private String username;
  @NotNull(message = "username cannot be empty")
  @Length(min = 8, message = "your password is too short")
  private String password;
  @NotNull(message = "username cannot be empty")
  @Email
  private String email;
  @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserProject> userProjectList = new ArrayList<>();
  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime updatedDate;
  @Enumerated(EnumType.ORDINAL)
  private Role role;

  private Boolean isDeleted = Boolean.FALSE;

  @PrePersist
  public void setUp() {
    if (role == null)
      role = Role.REGISTERED_USER;
  }

  public List<UserProject> getUserProjectList() {
    return userProjectList;
  }


  public Boolean getDeleted() {
    return isDeleted;
  }

  public void setDeleted(Boolean deleted) {
    isDeleted = deleted;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
