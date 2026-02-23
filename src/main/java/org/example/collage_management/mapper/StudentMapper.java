package org.example.collage_management.mapper;

import org.example.collage_management.dto.request.StudentRequestDto;
import org.example.collage_management.dto.response.StudentResponseDto;
import org.example.collage_management.dto.response.TeacherResponseDto;
import org.example.collage_management.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "department.name", target = "departmentName")
    StudentResponseDto toResponseDto(Student student);

    // for create
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "department", ignore = true)
        @Mapping(target = "status", ignore = true)
    Student toEntity(StudentRequestDto dto);

        //for update
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateStudentFromDto(StudentRequestDto dto,
                                            @MappingTarget Student student);

}
