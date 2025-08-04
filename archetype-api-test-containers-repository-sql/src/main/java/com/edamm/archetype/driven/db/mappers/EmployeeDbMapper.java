package com.edamm.archetype.driven.db.mappers;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.driven.db.models.EmployeeMO;
import org.mapstruct.Mapper;

import java.util.Map;


@Mapper(componentModel = "spring")
public interface EmployeeDbMapper {

    default Employee dbToDomain(Map<String, Object> queryResult) {
         var employeeBuilder = Employee.builder();

         if (queryResult == null || queryResult.isEmpty()) {
             return employeeBuilder.build();
         }

         if (queryResult.containsKey("id")) {
             employeeBuilder.id((Integer) queryResult.get("id"));
         }

         if (queryResult.containsKey("name")) {
             employeeBuilder.name((String) queryResult.get("name"));
         }

         if (queryResult.containsKey("email")) {
             employeeBuilder.email((String) queryResult.get("email"));
         }

         return employeeBuilder.build();
    }

    Employee dbToDomain(EmployeeMO employeeMO);

    EmployeeMO domainToDb(Employee employee);
    
}
