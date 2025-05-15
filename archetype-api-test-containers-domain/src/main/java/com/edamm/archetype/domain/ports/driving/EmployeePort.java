package com.edamm.archetype.domain.ports.driving;

import com.edamm.archetype.domain.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeePort {

    List<Employee> findAll();

    Optional<Employee> findOne(Integer id);

    Employee save(Employee employee);

    void deleteById(Integer id);

}
