    package org.example.collage_management.dto.response;


    import lombok.AllArgsConstructor;
    import lombok.Getter;

    @Getter
    @AllArgsConstructor

    public class StudentResponseDto {

        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String departmentName;

    }
