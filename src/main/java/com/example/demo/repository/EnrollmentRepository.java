package com.example.demo.repository;

import com.example.demo.entity.Enrollment;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

  @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId")
  List<Enrollment> findByStudentId(UUID studentId);

  List<Enrollment> findByCourseId(UUID courseId);

}
