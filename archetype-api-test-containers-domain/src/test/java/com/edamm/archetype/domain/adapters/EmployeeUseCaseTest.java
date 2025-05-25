package com.edamm.archetype.domain.adapters;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.domain.ports.driven.EmployeeRepositoryPort;
import com.edamm.archetype.domain.ports.driving.EmployeePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeUseCaseTest {

    private static final Integer EMPLOYEE_ID = 1;

    @Mock
    private EmployeeRepositoryPort employeeRepositoryPort;

    private EmployeePort employeePort;

    @BeforeEach
    void init() {
        employeePort = new EmployeeUseCase(employeeRepositoryPort);
    }

    @Test
    void getAllEmployeesShouldNotReturnEmployees() {
        when(employeeRepositoryPort.findAll()).thenReturn(List.of());
        var result = employeePort.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllEmployeesShouldReturnEmployees() {
        when(employeeRepositoryPort.findAll()).thenReturn(List.of(
                Employee.builder().build(),
                Employee.builder().build()
        ));
        var result = employeePort.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getEmployeeByIdShouldNotReturnEmployee() {
        when(employeeRepositoryPort.findOne(EMPLOYEE_ID)).thenReturn(Optional.empty());
        var result = employeePort.findOne(EMPLOYEE_ID);
        assertTrue(result.isEmpty());
    }

    @Test
    void getEmployeeByIdShouldReturnEmployee() {
        when(employeeRepositoryPort.findOne(EMPLOYEE_ID)).thenReturn(Optional.of(Employee.builder().build()));
        var result = employeePort.findOne(EMPLOYEE_ID);
        assertNotNull(result);
    }

    @Test
    void createEmployeeShouldReturnCreatedEmployee() {
        when(employeeRepositoryPort.save(any())).thenReturn(Employee.builder().build());

        var result = employeePort.save(Employee.builder().build());

        verify(employeeRepositoryPort, times(1)).save(any());
        assertNotNull(result);
    }

    @Test
    void deleteEmployeeShouldCallDelete() {
        employeePort.deleteById(EMPLOYEE_ID);
        verify(employeeRepositoryPort, times(1)).deleteById(EMPLOYEE_ID);
    }

}
