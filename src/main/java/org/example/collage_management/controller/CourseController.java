package org.example.collage_management.controller;

import org.example.collage_management.dto.request.CourseRequestDto;
import org.example.collage_management.dto.response.CourseResponseDto;
import org.example.collage_management.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity<CourseResponseDto> addCourse(
            @RequestBody CourseRequestDto dto) {

        CourseResponseDto saved = courseService.createCourse(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PostMapping("/add/bulk")
    public ResponseEntity<List<CourseResponseDto>> addMultipleCourse(
            @RequestBody List<CourseRequestDto> courses) {

        List<CourseResponseDto> saved =
                courseService.createMultipleCourses(courses);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(courseService.getCourseByID(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(
            @RequestBody CourseRequestDto dto,
            @PathVariable Long id) {

        return ResponseEntity.ok(
                courseService.updateCourse(dto, id)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
