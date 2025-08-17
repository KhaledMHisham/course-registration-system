package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
class CourseServiceTest {
  @Autowired
  private CourseRepository courseRepository;

  @Test
  void save() {
    // Given
    Course course = new Course();
    course.setTitle("Test Course");

    // When
    Course savedCourse = courseRepository.save(course);

    // Then
    assertNotNull(savedCourse.getId());
    assertEquals("Test Course", savedCourse.getTitle());
  }

  @Test
  void findAll() {
    // Given
    Course course1 = new Course();
    course1.setTitle("Course 1");
    courseRepository.save(course1);

    Course course2 = new Course();
    course2.setTitle("Course 2");
    courseRepository.save(course2);

    // When
    var courses = courseRepository.findAll();

    // Then
    assertFalse(courses.isEmpty());
    assertEquals(2, courses.size());
  }

  @Test
  void testFindAll() {

    // Given
    Course course1 = new Course();
    course1.setTitle("Course 1");
    courseRepository.save(course1);

    Course course2 = new Course();
    course2.setTitle("Course 2");
    courseRepository.save(course2);

    // When
    Pageable pageable = PageRequest.of(0, 1);
    var page = courseRepository.findAll(pageable);

    // Then
    assertEquals(1, page.getContent().size());
    assertEquals(2, page.getTotalElements());
  }


}