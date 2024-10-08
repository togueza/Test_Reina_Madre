package com.reina.madre.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Tasks {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "completed")
  private boolean completed;

  @Column(name = "priorities")
  private String priorities;

  public Tasks() {

  }

  public Tasks(String title, String description, boolean completed, String priorities) {
    this.title = title;
    this.description = description;
    this.completed = completed;
    this.priorities = priorities;
  }

  public long getId() {
    return id;
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

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean isCompleted) {
    this.completed = isCompleted;
  }

  public String getPriorities() { return priorities; }

  public void setPriorities(String priorities) { this.priorities = priorities; }

  @Override
  public String toString() {
    return "Tasks [id=" + id + ", title=" + title + ", desc=" + description + ", completed=" + completed + ", priorities=" + priorities + "]";
  }

}
