package org.example.collage_management.mapper;

import org.example.collage_management.dto.request.CourseRequestDto;
import org.example.collage_management.dto.response.CourseResponseDto;
import org.example.collage_management.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

        // single get
        @Mapping(source = "department.name", target = "departmentName")
        @Mapping(source = "department.id", target = "departmentId")
        @Mapping(source = "teacher.name", target = "teacherName")
        @Mapping(source = "teacher.id", target = "teacherId")
        CourseResponseDto toResponseDto(Course course);

        // get all
        List<CourseResponseDto> toResponseDto(List<Course> courses);

        // create
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "department", ignore = true)
        @Mapping(target = "teacher", ignore = true)
        Course toEntity(CourseRequestDto dto);

          // create multiple
         List<Course> toEntity(List<CourseRequestDto> dtoList);

        // update
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        @Mapping(target = "department", ignore = true)
        @Mapping(target = "teacher", ignore = true)
        void updateCourseFromDto(CourseRequestDto dto,
                                 @MappingTarget Course course);
    }


