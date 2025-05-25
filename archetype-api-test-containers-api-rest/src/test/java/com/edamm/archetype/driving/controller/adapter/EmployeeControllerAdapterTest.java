package com.edamm.archetype.driving.controller.adapter;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.domain.ports.driving.EmployeePort;
import com.edamm.archetype.driving.controller.adapters.EmployeeControllerAdapter;
import com.edamm.archetype.driving.controller.mappers.EmployeeDtoMapperImpl;
import com.edamm.archetype.model.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerAdapterTest {

    private static final Integer EMPLOYEE_ID = 1;

    @Mock
    private EmployeePort employeePort;

    private EmployeeControllerAdapter employeeControllerAdapter;

    @BeforeEach
    void init() {
        employeeControllerAdapter = new EmployeeControllerAdapter(employeePort, new EmployeeDtoMapperImpl());
    }

    @Test
    void getAllEmployeesShouldNotReturnEmployees() {
        when(employeePort.findAll()).thenReturn(List.of());
        var result = employeeControllerAdapter.getAllEmployees();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().isEmpty());
    }

    @Test
    void getAllEmployeesShouldReturnEmployees() {
        when(employeePort.findAll()).thenReturn(List.of(
                Employee.builder().build(),
                Employee.builder().build()
        ));
        var result = employeeControllerAdapter.getAllEmployees();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertFalse(result.getBody().isEmpty());
    }

    @Test
    void getEmployeeByIdShouldNotReturnEmployee() {
        when(employeePort.findOne(EMPLOYEE_ID)).thenReturn(Optional.empty());
        var result = employeeControllerAdapter.getEmployeeById(EMPLOYEE_ID);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    void getEmployeeByIdShouldReturnEmployee() {
        when(employeePort.findOne(EMPLOYEE_ID)).thenReturn(Optional.of(Employee.builder().build()));
        var result = employeeControllerAdapter.getEmployeeById(EMPLOYEE_ID);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void createEmployeeShouldReturnCreatedEmployee() {
        when(employeePort.save(any())).thenReturn(Employee.builder().build());

        var result = employeeControllerAdapter.createEmployee(new EmployeeDto());

        verify(employeePort, times(1)).save(any());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    void deleteEmployeeShouldCallDelete() {
        var result = employeeControllerAdapter.deleteEmployee(EMPLOYEE_ID);

        verify(employeePort, times(1)).deleteById(EMPLOYEE_ID);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

}
