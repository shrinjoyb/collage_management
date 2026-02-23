package org.example.collage_management.service;

import org.example.collage_management.dto.request.StudentRequestDto;
import org.example.collage_management.dto.response.StudentResponseDto;
import org.example.collage_management.entity.Student;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto dto);

    List<StudentResponseDto> createMultipleStudents(List<StudentRequestDto> students);

    StudentResponseDto update(Long id, StudentRequestDto student);

    StudentResponseDto findById(Long id);

    List<StudentResponseDto> findAll();

    void deleteById(Long id);
}
