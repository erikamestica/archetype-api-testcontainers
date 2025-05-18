package com.edamm.archetype.driving.controller.mappers;

import com.edamm.archetype.api.EmployeeDto;
import com.edamm.archetype.domain.models.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeDtoMapper {
    EmployeeDto domainToDto(Employee employee);
    Employee dtoToDomain(EmployeeDto employeeDto);
}
