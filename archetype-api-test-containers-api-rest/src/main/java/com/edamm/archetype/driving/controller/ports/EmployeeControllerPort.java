package com.edamm.archetype.driving.controller.ports;

import com.edamm.archetype.driving.controller.dtos.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public interface EmployeeControllerPort {

    @GetMapping
    default ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/{id}")
    default ResponseEntity<EmployeeDto> findOne(@PathVariable(name = "id") Integer employeeId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping
    default ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeMO) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping("/{id}")
    default ResponseEntity<Void> delete(@PathVariable(name = "id") Integer employeeId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
