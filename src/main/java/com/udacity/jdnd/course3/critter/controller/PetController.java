package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.DTOs.PetDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Long createdPetId=  petService.savePet(convertPetDtoToPet(petDTO));

        petDTO.setId(createdPetId);
        return petDTO;

    }





    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
       return convertPetToPetDTO(petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
       List<Pet> pets = petService.findAllPets();
       List<PetDTO> petDTOS = pets.stream()
                .map(PetController::convertPetToPetDTO)
                .collect(Collectors.toList());
                return petDTOS;

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets =petService.findPetByCustomerID(ownerId);
        List<PetDTO> petDTOS = pets.stream()
                .map(PetController::convertPetToPetDTO)
                .collect(Collectors.toList());
        return petDTOS;

    }

    private static PetDTO convertPetToPetDTO(Pet pet){
       PetDTO petDTO = new PetDTO();
       BeanUtils.copyProperties(pet, petDTO);
       return petDTO;
    }

    private  Pet convertPetDtoToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Long customerId = petDTO.getOwnerId();
        Customer customer = customerService.getCustomerById(customerId);
        pet.setCustomer(customer);
        pet.setName(petDTO.getName());
        return pet;

    }

}
