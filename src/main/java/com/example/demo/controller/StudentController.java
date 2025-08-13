package com.example.demo.controller;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {
  private final StudentService studentService;

     @GetMapping(path = "/students",  consumes = "application/json", produces = "application/json")
     public List<StudentDto> getAllStudents(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
       List<Student> studentEntityList;
       if (page != 0 && size != 0) {
         studentEntityList=  studentService.findAll(page, size).getContent();
       } else {
          studentEntityList = new ArrayList<>(studentService.findAll());
       }
       return studentEntityList.stream()
           .map(StudentMapper.INSTANCE::toDto)
           .toList();
     }
  @GetMapping(path = "/students",  consumes = "application/json", produces = "text/plain")
  public String getAllStudentsText(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
       return "Hello World";
  }

     @PostMapping(path = "/students", consumes = "application/json", produces = "application/json")
     public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
       Student entity = StudentMapper.INSTANCE.toEntity(studentDto);
       return new ResponseEntity<>(StudentMapper.INSTANCE.toDto(studentService.save(entity)), HttpStatus.CREATED);
     }
}
