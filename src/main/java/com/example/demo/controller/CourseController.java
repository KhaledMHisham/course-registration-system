package com.example.demo.controller;

import com.examle.demo.server.api.CoursesApi;
import com.examle.demo.server.dto.CourseApiDto;
import com.examle.demo.server.dto.CoursesApiDto;
import com.example.demo.entity.Course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.service.CourseService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController implements CoursesApi {
  private final CourseService courseService;

  public CourseController(CourseService courseService) {this.courseService = courseService;}

  @Override
  public ResponseEntity<Void> deleteCourseById(String id) {
    courseService.delete(UUID.fromString(id));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<CoursesApiDto> findAllCourses(String limit) {
    List<Course> content = courseService.findAll(0, Integer.parseInt(limit)).getContent();
    List<CourseApiDto> list = content.stream().map(course -> CourseMapper.INSTANCE.toDto(course)).toList();
    CoursesApiDto coursesApiDto = new CoursesApiDto();
    coursesApiDto.setCourses(list);
    return new ResponseEntity<>(coursesApiDto, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CourseApiDto> findCourseByTitle(String title) {
    Course byTitle = courseService.findByTitle(title);
    return new ResponseEntity<>(CourseMapper.INSTANCE.toDto(byTitle), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> saveCourses(CourseApiDto courseApiDto) {
    courseService.save(CourseMapper.INSTANCE.toEntity(courseApiDto));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
