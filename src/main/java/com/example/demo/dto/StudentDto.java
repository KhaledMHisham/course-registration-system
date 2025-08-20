package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * DTO for {@link com.example.demo.entity.Student}
 */
@Getter
@Setter
public class StudentDto implements Serializable {
  @NotBlank(message = "Student name must not be blank")
  String studentName;
  @Email(message = "Email should be valid")
  @NotBlank(message = "Email must not be blank")
  String email;
  String phone;
  @Min(value = 1, message = "Age must be greater than or equal to 1")
  String age;
  @NotBlank(message = "Address must not be blank")
  String address;
}