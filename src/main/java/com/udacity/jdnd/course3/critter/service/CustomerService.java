package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;


    public Long saveCustomer(Customer customer){
        //return customerRepository.save(customer).getPetId();

        Customer savedCustomer = customerRepository.save(customer);
        List<Pet> pets = savedCustomer.getPet();
        if(pets ==  null){
            pets  = new ArrayList<>();
        }
        savedCustomer.setPet(pets);
        customerRepository.save(savedCustomer);
        return savedCustomer.getCustomerId();
    }

    public List<Customer> getListOfCustomers(){
        return  customerRepository.findAll();

    }

    public Customer getCustomerByPetId(Long petId){
        return petRepository.getOne(petId).getCustomer();

//       Pet pet=null;
//       Optional<Pet> optionalPet =  petRepository.findById(petId);
//       if (optionalPet.isPresent()){
//           pet = optionalPet.get();
//
//       }
//        return pet.getCustomer();



    }

    public Customer getCustomerById(Long customerId){
        return customerRepository.getOne(customerId);
    }





}
