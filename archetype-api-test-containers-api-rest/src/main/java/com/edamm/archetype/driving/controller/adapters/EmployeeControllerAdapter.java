package com.edamm.archetype.driving.controller.adapters;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.domain.ports.driving.EmployeePort;
import com.edamm.archetype.driving.controller.dtos.EmployeeDto;
import com.edamm.archetype.driving.controller.mappers.EmployeeDtoMapper;
import com.edamm.archetype.driving.controller.ports.EmployeeControllerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeControllerAdapter implements EmployeeControllerPort {

    private final EmployeePort employeePort;
    private final EmployeeDtoMapper mapper;

    @Override
    public ResponseEntity<List<EmployeeDto>> findAll() {
        List<EmployeeDto> employees = employeePort.findAll().stream()
                .map(mapper::domainToDto)
                .toList();
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<EmployeeDto> findOne(Integer employeeId) {
        Optional<EmployeeDto> employee = employeePort.findOne(employeeId)
                .map(mapper::domainToDto);
        return ResponseEntity.of(employee);
    }

    @Override
    public ResponseEntity<EmployeeDto> create(EmployeeDto employee) {
        Employee savedEmployee = employeePort.save(mapper.dtoToDomain(employee));
        return ResponseEntity.ok(mapper.domainToDto(savedEmployee));
    }

    @Override
    public ResponseEntity<Void> delete(Integer employeeId) {
        employeePort.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }

}
