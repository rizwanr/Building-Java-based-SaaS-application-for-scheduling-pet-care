package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name="Employee")
public class Employee extends User {
    //@ElementCollection which allows us to establish a one-to-many relationship with basic objects
    // like Java primitives, wrapper, Date, String,Enums in this  case regarding EmployeeSkill and DayOfWeek
    @ElementCollection
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    public Employee(String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {

        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Employee(){

    }


    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
