package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.errorhandler.CourseNotFoundException;
import com.example.demo.repository.CourseRepository;
import java.util.ArrayList;
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
public class CourseService implements GenericService<Course> {

  private final CourseRepository courseRepository;

  @Override
  public Course findById(UUID id) {
    return courseRepository.findById(id).orElse(null);
  }

  @Override
  public Course save(Course entity) {
    if (entity.getId() != null) {
      throw new IllegalArgumentException("Course ID must be null for new entities");
    }
    return courseRepository.save(entity);
  }

  @Override
  public void delete(UUID id) {

    if (id == null) {
      throw new IllegalArgumentException("Course ID must not be null");
    }

    Course course = courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + id));
    courseRepository.delete(course);
  }


  @Override
  public Collection<Course> findAll() {
    return courseRepository.findAll();
  }

  @Override
  public Page<Course> findAll(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return courseRepository.findAll(pageable);
  }

  public Course findByTitle(String title) {
    return courseRepository.findByTitle(title)
        .orElseThrow(() -> new CourseNotFoundException("Course not found with title: " + title));
  }

  public List<Course> findByTitleContaining(String title) {
    return courseRepository.findByTitleContaining(title).orElse(new ArrayList<>());
  }

  public boolean isCourseFull(UUID courseId) {
    Optional<Course> courseOptional = courseRepository.findById(courseId);

    if (courseOptional.isPresent()) {
      throw new CourseNotFoundException("Course not found with ID: " + courseId);
    }
    return courseOptional.get().getEnrollments().size() >= courseOptional.get().getMaxEnrollments();
  }

  public List<Course> getCoursesWithoutEnrollments() {
    return courseRepository.findAll().stream()
        .filter(course -> course.getEnrollments().isEmpty())
        .toList();
  }

  public List<Course> getCoursesWithEnrollments() {
    return courseRepository.findAll().stream()
        .filter(course -> !course.getEnrollments().isEmpty())
        .toList();
  }
}
