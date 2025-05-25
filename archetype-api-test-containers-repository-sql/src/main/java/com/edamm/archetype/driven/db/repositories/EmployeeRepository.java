package com.edamm.archetype.driven.db.repositories;

import com.edamm.archetype.driven.db.models.EmployeeMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeMO, Integer> {}

