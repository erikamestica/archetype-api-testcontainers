package com.edamm.archetype.driving.db.adapters;

import com.edamm.archetype.domain.models.Employee;
import com.edamm.archetype.domain.ports.driven.EmployeeRepositoryPort;
import com.edamm.archetype.driving.db.mappers.EmployeeDbMapper;
import com.edamm.archetype.driving.db.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

    private final EmployeeRepository repository;
    private final EmployeeDbMapper mapper;

    @Override
    public List<Employee> findAll() {
        return repository.findAll().stream()
                .map(mapper::dbToDomain)
                .toList();
    }

    @Override
    public Optional<Employee> findOne(Integer id) {
        return repository.findById(id).map(mapper::dbToDomain);
    }

    @Override
    public Employee save(Employee employee) {
        return mapper.dbToDomain(repository.save(mapper.domainToDb(employee)));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
