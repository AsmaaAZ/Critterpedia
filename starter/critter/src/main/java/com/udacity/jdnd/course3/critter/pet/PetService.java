package com.udacity.jdnd.course3.critter.pet;
// @author asmaa **

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PetService {
  @Autowired
  private PetRepository petRepository;
  @Autowired
  private CustomerRepository customerRepository;

  public Pet save(Pet pet){
    Pet returnPet = petRepository.save(pet);
    Customer customer = returnPet.getCustomer();
    customer.addPet(returnPet);
    customerRepository.save(customer);
    return returnPet;
  }

  public Pet findPetById(long id){
    return petRepository.findPetById(id);
  }

  public List<Pet> findAll(){
    return petRepository.findAll();
  }

  public List<Pet> findAllByCustomerId(long id){
    return petRepository.findAllByCustomerId(id);
  }

  public void addPetToCustomer(Pet pet, Customer customer){
    List<Pet> pets = customer.getPets();
    if(pets != null){
      pets.add(pet);
    } else {
      pets = new ArrayList<Pet>();
      pets.add(pet);
    }
    customer.setPets(pets);
    customerRepository.save(customer);
  }
}
