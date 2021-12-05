package com.udacity.jdnd.course3.critter.entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="Customer")
public class Customer  {

    @Id
    @GeneratedValue
    private Long id;
    @Nationalized
    @Column(name = "fullName", length = 50)
    private String name;


    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    //make sure to specify mappedBy. Lazy fetch optional,
    //  but often a good idea for collection attributes
    // added CascadeType.REMOVE to automatically clear any associated Plants when removed
    //Bidirectional - Association specified on both sides of the relationship. Use mappedBy on the containing Entity side.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", targetEntity = Pet.class, cascade = CascadeType.ALL)
    private List<Pet> pet;

    public Customer( @Pattern(regexp = "(^$|[0-9]{10})") String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public Customer(){

    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public List<Pet> getPet() {
        return pet;
    }

    public void setPet(List<Pet> pet) {
        this.pet = pet;
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
