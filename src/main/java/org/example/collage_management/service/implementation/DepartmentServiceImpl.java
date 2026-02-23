package org.example.collage_management.service.implementation;

import org.example.collage_management.dto.request.DepartmentRequestDto;
import org.example.collage_management.dto.response.DepartmentResponseDto;
import org.example.collage_management.entity.Department;
import org.example.collage_management.exception.ResourceNotFoundException;
import org.example.collage_management.mapper.DepartmentMapper;
import org.example.collage_management.repository.DepartmentRepository;
import org.example.collage_management.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public  class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper  departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }
    //CREATE SINGLE DEPARTMENT
    @Transactional
    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto)
    {
        Department department = departmentMapper.toEntity(dto);

        return departmentMapper.toResponseDto(departmentRepository.save(department));


    }
    //CREATE MULTIPLE DEPARTMENT
    @Transactional
    @Override
    public List<DepartmentResponseDto> createMultipleDepartment(List<DepartmentRequestDto> dtos) {

        List<Department> department = departmentMapper.toEntity(dtos);

        return departmentMapper.toResponseDto(departmentRepository.saveAll(department));
    }
    //GET ALL DEPARTMENT
    @Transactional
    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
         List<Department> Department =departmentRepository.findAll();

         return departmentMapper.toResponseDto(Department);
    }
    //GET DEPARTMENT BY ID
    @Transactional
    @Override
    public DepartmentResponseDto getDepartmentById(Long id) {
        Department Department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id: " + id));

        return departmentMapper.toResponseDto(Department);
    }
    // UPDATE DEPARTMENT
    @Transactional
    @Override
    public DepartmentResponseDto updateDepartment (Long id, DepartmentRequestDto dto) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        departmentMapper.departmentUpdate(dto, existingDepartment);

        return departmentMapper.toResponseDto(departmentRepository.save(existingDepartment));

    }
    // DELETE DEPARTMENT
    @Override
    @Transactional
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id: " + id));

        departmentRepository.delete(department);
    }

}
