package org.example.collage_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.collage_management.entity.enums.DepartmentStatus;
import org.example.collage_management.entity.enums.DeptCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)      //dept id
    private Long id;

    @Column(nullable = false)  //dept name
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)       //dept code
    private DeptCode dcode;

    @Enumerated(EnumType.STRING)       // status
    @Column(nullable = false)
    private DepartmentStatus status;

    @CreationTimestamp                  // creation timestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp                  // updation timestamp
    private LocalDateTime updatedAt;
}
