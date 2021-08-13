package com.udacity.jdnd.course3.critter.schedule;
// @author asmaa **

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.*;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
public class Schedule {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private @Getter
  @Setter
  long id;

  @ManyToMany
  private @Getter @Setter
  List<Employee> employees;

  @ManyToMany
  private @Getter @Setter List<Pet> pets;

  private @Getter @Setter
  LocalDate date;

  @ElementCollection
  private @Getter @Setter
  Set<EmployeeSkill> activities;

}
