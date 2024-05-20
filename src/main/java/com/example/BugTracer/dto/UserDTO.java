package com.example.BugTracer.dto;

import java.util.Date;

public class UserDTO {
  public int id;
  public String username;
  public String password;
  public String email;

  public Date createdDate;

  public Date lastUpdated;

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

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
