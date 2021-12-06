package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
