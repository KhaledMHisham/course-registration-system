package com.example.demo;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import java.util.Collection;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

  @Autowired
  CourseService courseService;


  @Test
  void generator() {
    UUID uuid = UUID.randomUUID();
    System.out.println("Generated UUID: " + uuid);
  }

  @Test
  void contextLoads() {
    Course course = new Course();
    course.setTitle("test course");
    course.setDescription("test course description");

    Course savedCourse = courseService.save(course);
    Collection<Course> all = courseService.findAll();

    Assertions.assertNotNull(savedCourse);
    Assertions.assertEquals(course.getTitle(), savedCourse.getTitle());

    Assertions.assertNotNull(all);
    Assertions.assertEquals(1, all.size());
  }

}
