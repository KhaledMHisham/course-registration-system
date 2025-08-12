package com.example.demo.repository;

import com.example.demo.entity.Course;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
  Optional<Course> findByTitle(String title);

  Optional<List<Course>> findByTitleContaining(String title);
}
