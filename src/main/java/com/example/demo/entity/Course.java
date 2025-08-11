package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "course_id")
  private UUID id;
  private String title;
  private String description;
  @OneToMany(mappedBy = "course")
  private Set<Enrollment> enrollments;

  @Override
  public String toString() {
    return "Course{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
