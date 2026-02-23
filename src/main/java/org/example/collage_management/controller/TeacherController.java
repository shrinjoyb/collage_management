package org.example.collage_management.controller;
import org.example.collage_management.dto.request.TeacherRequestDto;
import org.example.collage_management.dto.response.TeacherResponseDto;
import org.example.collage_management.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // FIND ALL
    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAllTeachers());
    }

    // FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

    // CREATE SINGLE
    @PostMapping
    public ResponseEntity<TeacherResponseDto> createTeacher(
            @RequestBody TeacherRequestDto dto) {

        TeacherResponseDto saved = teacherService.createTeacher(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // CREATE BULK
    @PostMapping("/bulk")
    public ResponseEntity<List<TeacherResponseDto>> createMultipleTeachers(
            @RequestBody List<TeacherRequestDto> dtos) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teacherService.createMultipleTeachers(dtos));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(
            @PathVariable Long id,
            @RequestBody TeacherRequestDto dto) {

        return ResponseEntity.ok(
                teacherService.updateTeacher( dto, id)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
