package org.example.collage_management.repository;

import org.example.collage_management.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends
        JpaRepository<Teacher, Long> {
}
