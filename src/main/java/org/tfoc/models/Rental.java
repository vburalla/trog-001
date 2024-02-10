package org.tfoc.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * The rental class represents a customer renting a movie.
 */
@Data
@AllArgsConstructor
public class Rental {

    private Movie movie;
    private LocalDate rentalDate;

}
