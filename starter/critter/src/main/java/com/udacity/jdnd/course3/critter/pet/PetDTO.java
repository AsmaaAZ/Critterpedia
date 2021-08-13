package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;
import lombok.*;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
public class PetDTO {
    private @Getter
    @Setter
    long id;
    private @Getter @Setter PetType type;
    private @Getter @Setter String name;
    private @Getter @Setter long ownerId;
    private @Getter @Setter LocalDate birthDate;
    private @Getter @Setter String notes;

}
