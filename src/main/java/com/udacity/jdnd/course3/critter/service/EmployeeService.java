package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Long saveEmployee(Employee employee) {
        return employeeRepository.save(employee).getId();
    }

    public List<Employee> getListOfEmployees() {
        return employeeRepository.findAll();

    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public void saveAvailablityOfEmployee(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);


    }

    public Employee findEmployeeById(long employeeId) {
        return employeeRepository.getOne(employeeId);
    }


    public List<Employee> findEmployeesByAvailablityAndSkill(DayOfWeek dayOfWeekStaffAvailable, Set<EmployeeSkill> employeeSkills) {

      List<Employee> employees = new ArrayList<>();

      List<Employee> employeesAvailable = employeeRepository.findAllByDaysAvailable(dayOfWeekStaffAvailable);

      employeesAvailable.stream()
              .filter(employee -> employee.getSkills().containsAll(employeeSkills))
              .forEach(employee -> employees.add(employee));


//        for (Employee employee: employeesAvailable) {
//            if (employee.getSkills().containsAll(employeeSkills)){
//                employees.add(employee);
//            }
//

//        }
        return employees;

    }

}
