package org.example.collage_management.service;

import org.example.collage_management.dto.request.TeacherRequestDto;
import org.example.collage_management.dto.response.TeacherResponseDto;
import org.example.collage_management.entity.Teacher;

import java.util.List;

public interface TeacherService {

    TeacherResponseDto createTeacher(TeacherRequestDto dto);

    List<TeacherResponseDto> createMultipleTeachers(List<TeacherRequestDto> dto);

    TeacherResponseDto updateTeacher(TeacherRequestDto dto , Long id);

    void deleteTeacher(Long id);

    TeacherResponseDto findTeacherById(Long id);

    List<TeacherResponseDto> findAllTeachers();
}
