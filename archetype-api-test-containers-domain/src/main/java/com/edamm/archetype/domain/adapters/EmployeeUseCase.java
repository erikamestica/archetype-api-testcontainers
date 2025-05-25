package com.edamm.archetype.domain.adapters;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.domain.ports.driven.EmployeeRepositoryPort;
import com.edamm.archetype.domain.ports.driving.EmployeePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeUseCase implements EmployeePort {

    private final EmployeeRepositoryPort employeeRepositoryPort;

    @Override
    public List<Employee> findAll() {
        return employeeRepositoryPort.findAll();
    }

    @Override
    public Optional<Employee> findOne(Integer id) {
        return employeeRepositoryPort.findOne(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepositoryPort.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepositoryPort.deleteById(id);
    }

}
