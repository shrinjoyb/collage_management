package org.example.collage_management.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.collage_management.entity.enums.DepartmentStatus;
import org.example.collage_management.entity.enums.DeptCode;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentResponseDto {

    private Long id;
    private String name;
    private DeptCode dcode;
    private DepartmentStatus status;
}
