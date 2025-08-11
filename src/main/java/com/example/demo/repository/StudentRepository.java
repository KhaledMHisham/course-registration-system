package com.example.demo.repository;


import com.example.demo.entity.Student;
import java.util.List;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

  Student findByNameAndAge(String id,String age);

  @Query("SELECT s FROM Student s WHERE s.name = :name AND s.age = :age")
  Student getStudents(@Param("name")UUID id, @Param("age")String age);

  @Query("SELECT s FROM Student s WHERE s.name = :name")
  List<Student> findByName(String name);

  Page<Student> findAll(Pageable pageable);

}
