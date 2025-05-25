package com.edamm.archetype.domain.ports.driven;

import com.edamm.archetype.domain.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryPort {

    List<Employee> findAll();

    Optional<Employee> findOne(Integer id);

    Employee save(Employee employee);

    void deleteById(Integer id);

}
