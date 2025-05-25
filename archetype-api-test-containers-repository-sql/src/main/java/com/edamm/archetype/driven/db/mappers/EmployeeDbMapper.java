package com.edamm.archetype.driven.db.mappers;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.driven.db.models.EmployeeMO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EmployeeDbMapper {
    Employee dbToDomain(EmployeeMO employeeMO);
    EmployeeMO domainToDb(Employee employee);
}
