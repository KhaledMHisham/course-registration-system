package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "student_id")
  private UUID id;
  private String name;
  private String email;
  private String phone;
  private String age;
  private String address;

  @OneToMany(mappedBy = "student")
  private Set<Enrollment> enrollments;

  @Override
  public String toString() {
    return "Student{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", age='" + age + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}
