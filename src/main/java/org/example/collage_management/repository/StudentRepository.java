package org.example.collage_management.repository;
import org.example.collage_management.entity.Department;
import org.example.collage_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
}