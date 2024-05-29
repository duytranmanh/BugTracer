package com.example.BugTracer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


public class UserDTO {

  private Integer id;
  @NotNull(message = "username cannot be empty")
  @Length(min = 1, max = 20, message = "your username is too long")
  private String username;
  @NotNull(message = "password cannot be empty")
  @Length(min = 8, message = "your password is too short")
  private String password;
  @NotNull(message = "email cannot be empty")
  @Email(message = "not a valid email")
  private String email;

  private LocalDateTime createdDate;

  private LocalDateTime lastUpdated;

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

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public LocalDateTime getLastUpdated() {
    return lastUpdated;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;


    UserDTO userDTO = (UserDTO) o;

    if (!username.equals(userDTO.username)) return false;
    if (!email.equals(userDTO.email)) return false;
    return true;
  }

}
