package org.example.collage_management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.collage_management.entity.Department;
import org.example.collage_management.entity.Teacher;
import org.example.collage_management.entity.enums.CourseStatus;
import org.example.collage_management.entity.enums.CourseType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseRequestDto {

    private String courseName;
    private String courseCode;
    private String courseDescription;
    private CourseType courseType;
    private Integer semester;
    private Integer credits;
    private Long departmentId;
    private Long teacherId;
    private CourseStatus courseStatus;
}
