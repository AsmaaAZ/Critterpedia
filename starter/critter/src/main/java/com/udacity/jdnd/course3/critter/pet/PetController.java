package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.UtilClass;
import com.udacity.jdnd.course3.critter.user.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    Pet pet = UtilClass.convertDTOToPetEntity(petDTO);
    long customerId = petDTO.getOwnerId();
    Customer customer = customerService.findCustomerById(customerId);
    pet.setCustomer(customer);
    pet = petService.save(pet);
    petDTO.setId(pet.getId());
    return petDTO;
  }

  @GetMapping("/{petId}")
  public PetDTO getPet(@PathVariable long petId) {
    Pet pet = petService.findPetById(petId);
    PetDTO petDTO = UtilClass.convertEntityToPetDTO(pet);
    petDTO.setOwnerId(pet.getCustomer().getId());
    return petDTO;
  }

  @GetMapping
  public List<PetDTO> getPets() {
    List<Pet> petList = petService.findAll();
    List<PetDTO> petDTOList = petList.stream().map(pet -> UtilClass.convertEntityToPetDTO(pet))
        .collect(Collectors.toList());
    return petDTOList;
  }

  @GetMapping("/owner/{ownerId}")
  public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
    List<Pet> petList = petService.findAllByCustomerId(ownerId);
    List<PetDTO> petDTOList = petList.stream().map(pet ->
        UtilClass.convertEntityToPetDTO(pet)
    ).collect(Collectors.toList());
    List<PetDTO> updatedList = petDTOList.stream().map(petDTO -> {
      petDTO.setOwnerId(ownerId);
      return petDTO;
    }).collect(Collectors.toList());
    return updatedList;
  }
}
