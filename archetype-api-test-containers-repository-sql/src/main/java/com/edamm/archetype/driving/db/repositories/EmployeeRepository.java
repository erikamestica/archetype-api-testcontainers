package com.edamm.archetype.driving.db.repositories;

import com.edamm.archetype.driving.db.models.EmployeeMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeMO, Integer> {}

