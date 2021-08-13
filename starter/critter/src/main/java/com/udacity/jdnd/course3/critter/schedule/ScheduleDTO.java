package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.LocalDate;
import java.util.*;
import lombok.*;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private @Getter
    @Setter
    long id;
    private @Getter @Setter List<Long> employeeIds;
    private @Getter @Setter List<Long> petIds;
    private @Getter @Setter LocalDate date;
    private @Getter @Setter Set<EmployeeSkill> activities;
}
