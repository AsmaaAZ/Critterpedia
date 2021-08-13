package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;
import lombok.*;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
public class EmployeeDTO {
    private @Getter
    @Setter
    long id;
    private @Getter @Setter String name;
    private @Getter @Setter Set<EmployeeSkill> skills;
    private @Getter @Setter Set<DayOfWeek> daysAvailable;
}
