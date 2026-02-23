package org.example.collage_management.controller;

import org.example.collage_management.dto.request.DepartmentRequestDto;
import org.example.collage_management.dto.response.DepartmentResponseDto;
import org.example.collage_management.entity.Department;
import org.example.collage_management.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

   //CREATE SINGLE DEPARTMENT
   @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @RequestBody DepartmentRequestDto dto) {

        DepartmentResponseDto saved =
                departmentService.createDepartment(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
    // CREATE MULIPLE DEPARTMENT
    @PostMapping("/create/bulk")
    public ResponseEntity<List<DepartmentResponseDto>> createMultipleDepartment(
            @RequestBody List<DepartmentRequestDto> dtos)
    {
        List<DepartmentResponseDto> saved =
                departmentService.createMultipleDepartment(dtos);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
    //GET ALL
    @GetMapping("/get/all")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.getAllDepartments());

    }
    //GET BY ID
    @GetMapping("/get")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(
            @PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.getDepartmentById(id));
    }

    //UPDATE DEPARTMENT BY ID

    @PutMapping("/update/{id}")

    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @RequestBody DepartmentRequestDto dto,
            @PathVariable long id) {
        DepartmentResponseDto update = departmentService.updateDepartment(id,dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(update);

    }

    // DELETE DEPARTMENT BY ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }

}