package com.udacity.jdnd.course3.critter.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Schedule")
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;



    //    private long id;
    //    private List<Long> employeeIds;
    //    private List<Long> petIds;
    //    private LocalDate date;
    //    private Set<EmployeeSkill> activities;

   // Schedules that indicate one or more employees will be meeting one or more pets to perform one or more activities on a specific day.


}
