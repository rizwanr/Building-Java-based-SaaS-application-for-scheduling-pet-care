package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;




    public Long saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule).getId();

    };

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }


    public List<Schedule> findScheduleByPetId(long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow( ResourceNotFoundException::new);
      return scheduleRepository.getDetailsByPet(pet);


    }

    public List<Schedule> findScheduleByEmplpyeeId(long employeeId) {
        return scheduleRepository.getDetailsByEmployee(employeeRepository.getOne(employeeId));
    }

    public List<Schedule> findScheduleByCustomerId(long customerId) {
        return scheduleRepository.getDetailsByCustomer(customerRepository.getOne(customerId));

    }
}
