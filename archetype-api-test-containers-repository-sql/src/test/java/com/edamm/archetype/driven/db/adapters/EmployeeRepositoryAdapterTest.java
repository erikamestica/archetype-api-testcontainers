package com.edamm.archetype.driven.db.adapters;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.domain.ports.driven.EmployeeRepositoryPort;
import com.edamm.archetype.driven.db.mappers.EmployeeDbMapperImpl;
import com.edamm.archetype.driven.db.models.EmployeeMO;
import com.edamm.archetype.driven.db.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryAdapterTest {

    private static final Integer EMPLOYEE_ID = 1;

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeRepositoryPort employeeRepositoryPort;

    @BeforeEach
    void init() {
        employeeRepositoryPort = new EmployeeRepositoryAdapter(employeeRepository, new EmployeeDbMapperImpl());
    }

    @Test
    void getAllEmployeesShouldNotReturnEmployees() {
        when(employeeRepository.findAllWithFields(eq(EmployeeMO.class), anySet(), anySet())).thenReturn(List.of());
        var result = employeeRepositoryPort.findAll(Set.of("id"), Set.of("id"));
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllEmployeesShouldReturnEmployees() {
        when(employeeRepository.findAllWithFields(eq(EmployeeMO.class), anySet(), anySet())).thenReturn(List.of(
            Map.of(), Map.of()
        ));
        var result = employeeRepositoryPort.findAll(Set.of("id"), Set.of("+id"));
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getEmployeeByIdShouldNotReturnEmployee() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.empty());
        var result = employeeRepositoryPort.findOne(EMPLOYEE_ID);
        assertTrue(result.isEmpty());
    }

    @Test
    void getEmployeeByIdShouldReturnEmployee() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(EmployeeMO.builder().build()));
        var result = employeeRepositoryPort.findOne(EMPLOYEE_ID);
        assertNotNull(result);
    }

    @Test
    void createEmployeeShouldReturnCreatedEmployee() {
        when(employeeRepository.save(any())).thenReturn(EmployeeMO.builder().build());

        var result = employeeRepositoryPort.save(Employee.builder().build());

        verify(employeeRepository, times(1)).save(any());
        assertNotNull(result);
    }

    @Test
    void deleteEmployeeShouldCallDelete() {
        employeeRepositoryPort.deleteById(EMPLOYEE_ID);
        verify(employeeRepository, times(1)).deleteById(EMPLOYEE_ID);
    }

}
