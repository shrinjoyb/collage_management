package org.example.collage_management.mapper;

import org.example.collage_management.dto.request.StudentRequestDto;
import org.example.collage_management.dto.request.TeacherRequestDto;
import org.example.collage_management.dto.response.TeacherResponseDto;
import org.example.collage_management.entity.Student;
import org.example.collage_management.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

    @Mapper(componentModel = "spring")
    public interface TeacherMapper {

        // Single
        TeacherResponseDto toResponseDto(Teacher teacher);

        // List
        List<TeacherResponseDto> toResponseDtoList(List<Teacher> teachers);

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "departments", ignore = true)
        @Mapping(target = "status", ignore = true)
        Teacher toEntity(TeacherRequestDto dto);

        // update
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "departments", ignore = true)
        @Mapping(target = "status", ignore = true)
        void updateTeacherFromDto(TeacherRequestDto dto, @MappingTarget Teacher teacher);

    }


