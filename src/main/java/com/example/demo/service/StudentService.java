package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService implements GenericService<Student> {

  private final StudentRepository studentRepository;

  @Override
  public Page<Student> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return studentRepository.findAll(pageable);
  }

  @Override
  public Student findById(UUID id) {
    return studentRepository.findById(id).orElse(null);
  }

  @Override
  public Student save(Student entity) {
    if (entity.getId() != null) {
      throw new IllegalArgumentException("Student ID must be null for new entities");
    }
    return studentRepository.save(entity);
  }


  @Override
  public void delete(UUID id) {
    Optional<Student> byId = studentRepository.findById(id);

    if (byId.isPresent()) {
      studentRepository.delete(byId.get());
    } else {
      throw new IllegalArgumentException("Student not found with ID: " + id);
    }
  }

  public List<Student> findByName(String name) {
    return studentRepository.findByName(name);
  }

  public List<Student> getStudentsWithoutEnrollment() {
    return studentRepository.findAll().stream()
        .filter(student -> student.getEnrollments().isEmpty())
        .toList();
  }

  @Override
  public Collection<Student> findAll() {
    return studentRepository.findAll();
  }

}
