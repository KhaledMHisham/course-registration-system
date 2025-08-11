package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

@SpringBootTest
class StudentServiceTest {

  @Autowired
  private StudentService studentService;
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private EnrollmentRepository enrollmentRepository;

  @BeforeEach
   void setUp() {
    // Initialize test data if necessary
    Student student = new Student();
    student.setName("John Doe");
    student.setAge("20");
    studentRepository.save(student);

    Course course = new Course();
    course.setTitle("Mathematics");
    courseRepository.save(course);
  }

  @Test
  void findByName() {
    var student = studentRepository.findByName("John Doe").stream().findFirst().orElse(null);
    var course = courseRepository.findByTitle("Mathematics").orElse(null);
    var enrollment = studentService.enroll(student.getId(), UUID.randomUUID());
      System.out.println("Enrollment: " + enrollment);
  }
}