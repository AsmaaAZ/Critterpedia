package com.udacity.jdnd.course3.critter.user;
// @author asmaa **

import java.time.DayOfWeek;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private @Getter
  @Setter
  long id;
  private @Getter @Setter String name;

  @ElementCollection
  private @Getter @Setter
  Set<EmployeeSkill> skills;

  @ElementCollection
  private @Getter @Setter Set<DayOfWeek> daysAvailable;

}
