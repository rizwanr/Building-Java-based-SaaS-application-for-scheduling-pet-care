package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Pet")
public class Pet {

    @Id
    @GeneratedValue
    private Long id;
    private PetType type;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Customer.class) //many pets can belong to 1 customer
    @JoinColumn(name="customer_id")
    private Customer customer;
    private LocalDateTime birthDate;
    private String notes;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



}
