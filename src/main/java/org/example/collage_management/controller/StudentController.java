package org.example.collage_management.controller;

import org.example.collage_management.dto.request.StudentRequestDto;
import org.example.collage_management.dto.response.StudentResponseDto;
import org.example.collage_management.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<StudentResponseDto> createStudent(
            @RequestBody StudentRequestDto dto) {

        StudentResponseDto saved =
                studentService.createStudent(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // CREATE MULTIPLE STUDENTS
    @PostMapping("/create/bulk")
    public ResponseEntity<List<StudentResponseDto>> createMultipleStudents(
            @RequestBody List<StudentRequestDto> dtos) {

        List<StudentResponseDto> savedStudents =
                studentService.createMultipleStudents(dtos);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedStudents);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDto dto) {

        StudentResponseDto updated =
                studentService.update(id, dto);

        return ResponseEntity.ok(updated);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(
            @PathVariable Long id) {

        StudentResponseDto student =
                studentService.findById(id);

        return ResponseEntity.ok(student);
    }

    // GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDto>> findAllStudents() {

        List<StudentResponseDto> students =
                studentService.findAll();

        return ResponseEntity.ok(students);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        studentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
