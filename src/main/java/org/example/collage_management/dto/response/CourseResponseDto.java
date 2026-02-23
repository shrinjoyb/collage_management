package org.example.collage_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.collage_management.entity.enums.CourseStatus;
import org.example.collage_management.entity.enums.CourseType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {

    private Long id;
    private String courseName;
    private String courseCode;

    private Integer credits;
    private Integer semester;

    private CourseType courseType;
    private CourseStatus courseStatus;

    // Department info (only required fields)
    private Long departmentId;
    private String departmentName;

    // Teacher info
    private Long teacherId;
    private String teacherName;

    private String createdAt;
}
