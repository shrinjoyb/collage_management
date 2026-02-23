package org.example.collage_management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.collage_management.entity.enums.Designation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeacherRequestDto {
    private String name;
    private String email;
    private String phoneNumber;

    private Designation designation;
    private Integer experienceYear;
    private List<Long> departmentIds;

    private Date joinDate;
    private String qualification;
}
