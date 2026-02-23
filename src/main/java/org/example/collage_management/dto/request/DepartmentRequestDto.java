package org.example.collage_management.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.collage_management.entity.enums.DepartmentStatus;
import org.example.collage_management.entity.enums.DeptCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDto {

    private String name;
    private DeptCode dcode;
    private DepartmentStatus status;

}
