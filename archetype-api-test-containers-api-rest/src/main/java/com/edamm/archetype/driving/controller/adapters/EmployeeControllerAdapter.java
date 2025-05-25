package com.edamm.archetype.driving.controller.adapters;

import com.edamm.archetype.api.EmployeesApi;
import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.domain.ports.driving.EmployeePort;
import com.edamm.archetype.driving.controller.mappers.EmployeeDtoMapper;
import com.edamm.archetype.model.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EmployeeControllerAdapter implements EmployeesApi {

    private final EmployeePort employeePort;
    private final EmployeeDtoMapper mapper;

    @Override
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeePort.findAll().stream()
                .map(mapper::domainToDto)
                .toList();
        return ResponseEntity.ok(employees);
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Integer id) {
        Optional<EmployeeDto> employee = employeePort.findOne(id)
                .map(mapper::domainToDto);
        return ResponseEntity.of(employee);
    }

    @Override
    public ResponseEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        Employee savedEmployee = employeePort.save(mapper.dtoToDomain(employeeDto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).body(mapper.domainToDto(savedEmployee));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Integer id) {
        employeePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
