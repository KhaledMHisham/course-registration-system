package com.example.demo.service;

import com.example.demo.entity.Enrollment;
import com.example.demo.repository.EnrollmentRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService implements GenericService<Enrollment> {
  private final EnrollmentRepository enrollmentRepository;

  @Override
  public Enrollment findById(UUID id) {
    return enrollmentRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("Enrollment not found with ID: " + id));
  }

  @Override
  public Enrollment save(Enrollment entity) {
    if (entity.getEnrollmentId() != null) {
      throw new IllegalArgumentException("Enrollment ID must be null for new entities");
    }
    return enrollmentRepository.save(entity);
  }

  @Override
  public void delete(UUID id) {
    Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("Enrollment not found with ID: " + id));

    if (enrollment.getEnrollmentId() != null) {
      enrollmentRepository.delete(enrollment);
    } else {
      throw new IllegalArgumentException("Enrollment ID must not be null for deletion");
    }
  }

  @Override
  public Collection<Enrollment> findAll() {
    return enrollmentRepository.findAll();
  }

  @Override
  public Page<Enrollment> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return enrollmentRepository.findAll(pageable);
  }

  public Collection<Enrollment> findByStudentId(UUID studentId) {
    return enrollmentRepository.findByStudentId(studentId);
  }

  public Collection<Enrollment> findByCourseId(UUID courseId) {
    return enrollmentRepository.findByCourseId(courseId);
  }

  public boolean isEnrollmentActive(UUID enrollmentId) {
    Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
    if (enrollmentOptional.isPresent()) {
      return enrollmentOptional.get().isActive();
    } else {
      throw new IllegalArgumentException("Enrollment not found with ID: " + enrollmentId);
    }
  }
}
