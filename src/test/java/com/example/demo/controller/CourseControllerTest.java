package com.example.demo.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(CourseController.class)
class CourseControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockitoBean
  private CourseService courseService;

  @Test
  void deleteCourseById() throws Exception {

    UUID courseId = UUID.randomUUID();
    doNothing().when(courseService).delete(courseId);

    mockMvc.perform(delete("/course/{id}", courseId.toString()))
        .andExpect(status().isNoContent());

  }

  @Test
  void findAllCourses() throws Exception {
    Course course1 = new Course();
    course1.setTitle("Course 1");
    Course course2 = new Course();
    course2.setTitle("Course 2");

    Page<Course> page = new PageImpl<>(Arrays.asList(course1, course2));
    Mockito.when(courseService.findAll(0, 2)).thenReturn(page);

   mockMvc.perform(get("/course?limit=2"))
        .andExpect(status().isOk());


  }

  @Test
  void findCourseByTitle() throws Exception {
    String title = "Test Course";
    Course course = new Course();
    course.setTitle(title);
    Mockito.when(courseService.findByTitle(title)).thenReturn(course);


    mockMvc.perform(get("/course/{title}", title))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(title))
        .andReturn();
  }
}