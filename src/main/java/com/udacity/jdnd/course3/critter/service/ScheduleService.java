package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Schedule;
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




    public Long saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule).getId();

    };

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }


    public List<Schedule> findScheduleByPetId(long petId) {
       return scheduleRepository.getDetailsByPet(petRepository.getOne(petId));

    }

    public List<Schedule> findScheduleByEmplpyeeId(long employeeId) {
        return scheduleRepository.getDetailsByEmployee(employeeRepository.getOne(employeeId));
    }
}
