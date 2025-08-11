package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;
  private final EnrollmentRepository enrollmentRepository;


  public Page<Student> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return studentRepository.findAll(pageable);
  }

  public void save(Student student) {
    studentRepository.save(student);
  }

  public Student updateOrSave(Student student) {
    if (student.getId() == null) {
      throw new IllegalArgumentException("Student ID must not be null for update");
    }
    return studentRepository.save(student);
  }

  public void delete(Student student) {
    if (student.getId() == null) {
      throw new IllegalArgumentException("Student ID must not be null for deletion");
    }
    studentRepository.delete(student);
  }

  public Enrollment enroll(UUID studentId, UUID courseId) {
    Student student= studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));

    Enrollment enrollment = new Enrollment();
    enrollment.setStudent(student);
    enrollment.setCourse(course);
    enrollment.setEnrollmentDate(LocalDateTime.now());
    enrollmentRepository.save(enrollment);

    return enrollment;
  }

}
