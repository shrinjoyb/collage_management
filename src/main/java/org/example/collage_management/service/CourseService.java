package org.example.collage_management.service;

import org.example.collage_management.dto.request.CourseRequestDto;
import org.example.collage_management.dto.response.CourseResponseDto;
import org.example.collage_management.entity.Course;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto dto);

    List<CourseResponseDto> createMultipleCourses(List<CourseRequestDto> dtoList);

    CourseResponseDto getCourseByID(Long id);

    List<CourseResponseDto> getAllCourses();

    CourseResponseDto updateCourse(CourseRequestDto dto, Long id);

    void deleteCourse(Long id);

}
