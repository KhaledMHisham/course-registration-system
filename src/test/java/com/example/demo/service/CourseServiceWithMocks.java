package com.example.demo.service;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.demo.entity.Course;
import com.example.demo.errorhandler.CourseNotFoundException;
import com.example.demo.repository.CourseRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

@ExtendWith(MockitoExtension.class)
public class CourseServiceWithMocks {
  @Mock
  private CourseRepository courseRepository;
  @InjectMocks
  private  CourseService courseService;

  @Test
  public void findByTitle() {
    Course course1 = new Course();
    course1.setDescription("Course 1");
    courseRepository.save(course1);

    // When
    when(courseRepository.findByTitle("Course1")).thenThrow(CourseNotFoundException.class);

    Assertions.assertThrows(
        CourseNotFoundException.class,
        () -> courseService.findByTitle("Course1")
    );
  }


}
