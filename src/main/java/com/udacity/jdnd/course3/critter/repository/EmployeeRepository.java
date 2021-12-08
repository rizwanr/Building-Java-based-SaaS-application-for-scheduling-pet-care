package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("Select E from Employee E where :day member of E.daysAvailable")
    List<Employee> findAllByDaysAvailable(@Param("day") DayOfWeek dayOfWeekStaffAvailable);
}
