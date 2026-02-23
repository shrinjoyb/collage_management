package org.example.collage_management.dto.request;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long departmentId;

}
