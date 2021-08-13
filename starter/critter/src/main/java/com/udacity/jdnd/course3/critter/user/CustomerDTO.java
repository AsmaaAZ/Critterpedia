package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import lombok.*;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
public class CustomerDTO {
    private @Getter
    @Setter
    long id;
    private @Getter @Setter String name;
    private @Getter @Setter String phoneNumber;
    private @Getter @Setter String notes;
    private @Getter @Setter List<Long> petIds;

}
