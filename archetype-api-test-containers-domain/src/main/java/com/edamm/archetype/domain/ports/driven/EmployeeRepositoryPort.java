package com.edamm.archetype.domain.ports.driven;

import com.edamm.archetype.domain.models.Employee;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepositoryPort {

    List<Employee> findAll(Set<String> fields, Set<String> orderByFields);

    Optional<Employee> findOne(Integer id);

    Employee save(Employee employee);

    void deleteById(Integer id);

}
