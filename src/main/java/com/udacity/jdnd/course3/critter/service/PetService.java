package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    //Whenever a bidirectional association is formed,
    // the developer must ensure sure that both sides are in-sync at all times.

    public Pet savePet(Pet pet){
        Pet savedPet = petRepository.save(pet);
        Customer customer = savedPet.getCustomer();
        List<Pet> listOfCustomerPets = customer.getPet();

        if(listOfCustomerPets == null){
            listOfCustomerPets= new ArrayList<>();
        }

        listOfCustomerPets.add(savedPet);
        customer.setPet(listOfCustomerPets);
        customerRepository.save(customer);
        return savedPet;

    }




}
