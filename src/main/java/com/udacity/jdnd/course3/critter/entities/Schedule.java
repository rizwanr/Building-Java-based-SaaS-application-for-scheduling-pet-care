package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Schedule")
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    //    a Schedule can have more than one Pet and Employee and both Pet and Employee can have more than one Schedule
    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employee;
    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> Pet;
    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<com.udacity.jdnd.course3.critter.entities.Pet> getPet() {
        return Pet;
    }

    public void setPet(List<com.udacity.jdnd.course3.critter.entities.Pet> pet) {
        Pet = pet;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }








}
