package com.udacity.jdnd.course3.critter.user;

import java.time.LocalDate;
import java.util.Set;
import lombok.*;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    private @Getter @Setter
    Set<EmployeeSkill> skills;
    private @Getter @Setter LocalDate date;
}
