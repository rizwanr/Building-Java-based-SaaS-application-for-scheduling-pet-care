package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Long saveCustomer(Employee employee){
        return employeeRepository.save(employee).getId();
    }

    public List<Employee> getListOfEmployees(){
        return  employeeRepository.findAll();

    }
}
