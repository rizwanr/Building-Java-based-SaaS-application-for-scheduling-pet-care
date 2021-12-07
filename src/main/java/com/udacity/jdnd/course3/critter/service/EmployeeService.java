package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Long saveEmployee(Employee employee){
        return employeeRepository.save(employee).getId();
    }

    public List<Employee> getListOfEmployees(){
        return  employeeRepository.findAll();

    }

    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public void saveAvailablityOfEmployee(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee= employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);


    }

    public Employee findEmployeeById(long employeeId){
        return employeeRepository.getOne(employeeId);
    }

//    public LocalDate findEmployeesAvailability(EmployeeRequestDTO employeeDTO) {
//       Employee employee=  employeeDTO.getDate();
//
//
//    }
}
