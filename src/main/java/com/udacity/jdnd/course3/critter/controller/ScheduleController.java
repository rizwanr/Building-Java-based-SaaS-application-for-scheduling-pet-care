package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.DTOs.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Long scheduledId=  scheduleService.saveSchedule(convertScheduleDtoToSchedule(scheduleDTO));
        scheduleDTO.setId(scheduledId);
        return scheduleDTO;


    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        List<Schedule> allSchedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOS = allSchedules.stream()
                .map(ScheduleController::convertSchduleToScheduleDTO)
                .collect(Collectors.toList());
        return scheduleDTOS;


    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedulesByPet = scheduleService.findScheduleByPetId(petId);
        List<ScheduleDTO> scheduleDTOS = schedulesByPet.stream()
                .map(ScheduleController::convertSchduleToScheduleDTO)
                .collect(Collectors.toList());
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedulesByEmployee = scheduleService.findScheduleByEmplpyeeId(employeeId);
        List<ScheduleDTO> scheduleDTOS  = schedulesByEmployee.stream()
                .map(ScheduleController::convertSchduleToScheduleDTO)
                .collect(Collectors.toList());
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedulesByCustomer = scheduleService.findScheduleByCustomerId(customerId);
        List<ScheduleDTO> scheduleDTOS = schedulesByCustomer.stream()
                .map(ScheduleController::convertSchduleToScheduleDTO)
                .collect(Collectors.toList());
        return scheduleDTOS;
    }

    public   Schedule convertScheduleDtoToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();
        List<Employee> employeeList = new ArrayList<>();
        List<Pet> petList = new ArrayList<>();

        if(petIds!= null){
            for (Long petId: petIds) {
                petList.add(petService.findPetById(petId));

            }
        }

        if(employeeIds!= null){
            for (Long employeeId: employeeIds){
                employeeList.add(employeeService.findEmployeeById(employeeId));
            }
        }

        schedule.setPet(petList);
        schedule.setEmployee(employeeList);
        schedule.setActivities(scheduleDTO.getActivities());
        return schedule;
}




    public static ScheduleDTO convertSchduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> petIds = new ArrayList<>();
        List<Pet> pets = schedule.getPet();
        for (Pet pet:pets) {
            petIds.add(pet.getPetId());
        }
        List<Long> employeeIds = new ArrayList<>();
        List<Employee> employees = schedule.getEmployee();
        for(Employee employee : employees){
            employeeIds.add(employee.getId());
        }
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        return scheduleDTO;

    }
}
