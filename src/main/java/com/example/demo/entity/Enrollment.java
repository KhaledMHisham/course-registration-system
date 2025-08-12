package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Enrollment {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID enrollmentId;
  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;
  private LocalDateTime enrollmentDate;
  boolean isActive;

  @Override
  public String toString() {
    return "Enrollment{" +
            "enrollmentId=" + enrollmentId +
            ", student=" + student +
            ", course=" + course +
            ", enrollmentDate=" + enrollmentDate +
            '}';
  }
}
