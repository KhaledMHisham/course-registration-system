package com.example.demo.errorhandler;

public class CourseNotFoundException extends RuntimeException {
  public CourseNotFoundException(String message) {
    super(message);
  }
}
