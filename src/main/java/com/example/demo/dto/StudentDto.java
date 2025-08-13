package com.example.demo.dto;

import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link com.example.demo.entity.Student}
 */
@Value
public class StudentDto implements Serializable {
  String studentName;
  String email;
  String phone;
  String age;
  String address;
}