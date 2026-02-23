package org.example.collage_management.repository;

import org.example.collage_management.entity.Course;
import org.example.collage_management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
