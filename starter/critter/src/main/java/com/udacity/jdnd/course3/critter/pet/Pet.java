package com.udacity.jdnd.course3.critter.pet;
// @author asmaa **

import com.udacity.jdnd.course3.critter.user.Customer;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Entity
public class Pet {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private @Getter
  @Setter
  long id;
  private @Getter @Setter
  PetType type;
  private @Getter @Setter String name;

  @ManyToOne(fetch= FetchType.LAZY)
  private @Getter @Setter
  Customer customer;

  private @Getter @Setter
  LocalDate birthDate;
  private @Getter @Setter String notes;

}
