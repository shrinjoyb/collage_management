package org.example.collage_management.mapper;

import org.example.collage_management.dto.request.DepartmentRequestDto;
import org.example.collage_management.dto.response.DepartmentResponseDto;
import org.example.collage_management.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponseDto toResponseDto(Department department);
    List<DepartmentResponseDto> toResponseDto(List<Department> departments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Department toEntity(DepartmentRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    List<Department> toEntity(List<DepartmentRequestDto> dtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void departmentUpdate(DepartmentRequestDto departmentDto,
                            @MappingTarget Department departmentEntity );





}
