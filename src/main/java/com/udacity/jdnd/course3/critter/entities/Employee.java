package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nationalized
    @Column(name = "fullName", length = 50)
    private String name;

    //@ElementCollection which allows us to establish a one-to-many relationship with basic objects
    // like Java primitives, wrapper, Date, String,Enums in this  case regarding EmployeeSkill and DayOfWeek
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "employee")
    private List<Schedule> schedules;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
