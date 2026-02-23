package org.example.collage_management.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.collage_management.entity.Department;
import org.example.collage_management.entity.enums.Designation;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDto {

    ;
    private String name;
    private String email;
    private String phoneNumber;
    private Designation designation;
    private List<Department> departments;
    private String qualification;
    private Date joinDate;

    @JsonProperty("departments")
    public List<String> getDepartmentNames() {
        return departments.stream()
                .map(Department::getName)
                .toList();
    }
}
