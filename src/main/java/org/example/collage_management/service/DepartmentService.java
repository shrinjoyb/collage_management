package org.example.collage_management.service;
import org.example.collage_management.dto.request.DepartmentRequestDto;
import org.example.collage_management.dto.response.DepartmentResponseDto;
import org.example.collage_management.entity.Department;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentRequestDto dto);


    List<DepartmentResponseDto> createMultipleDepartment(List<DepartmentRequestDto> dtos );

    List<DepartmentResponseDto> getAllDepartments();


    DepartmentResponseDto getDepartmentById(Long id);


    DepartmentResponseDto updateDepartment (Long id, DepartmentRequestDto dto);

    void deleteDepartment (Long id);
}
