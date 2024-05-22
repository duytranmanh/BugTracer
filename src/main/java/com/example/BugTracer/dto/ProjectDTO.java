package com.example.BugTracer.dto;

import java.util.Date;

public class ProjectDTO {
  private int id;
  private int name;
  private Date createdDate;
  private Date lastUpdated;

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

  public int getName() {
    return name;
  }

  public void setName(int name) {
    this.name = name;
  }
}
