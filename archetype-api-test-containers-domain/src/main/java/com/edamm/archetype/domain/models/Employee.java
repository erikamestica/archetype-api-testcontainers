package com.edamm.archetype.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private Integer id;
    private String name;
    private String email;
}
