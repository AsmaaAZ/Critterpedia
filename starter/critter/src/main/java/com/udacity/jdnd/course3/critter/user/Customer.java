package com.udacity.jdnd.course3.critter.user;
// @author asmaa **


import com.udacity.jdnd.course3.critter.pet.Pet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private @Getter
  @Setter
  long id;
  private @Getter @Setter String name;
  private @Getter @Setter String phoneNumber;
  private @Getter @Setter String notes;

  @OneToMany(fetch= FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
  private @Getter @Setter
  List<Pet> pets;

  public void addPet(Pet pet) {
    if(this.pets == null){
      this.pets = new ArrayList<>();
    }
    this.pets.add(pet);
  }
}
