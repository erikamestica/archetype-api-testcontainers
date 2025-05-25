package com.edamm.archetype.driven.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (
    name = "employee",
    uniqueConstraints = @UniqueConstraint(name = "uk_employee_email", columnNames = "email")
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;

}


