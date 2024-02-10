package org.tfoc.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tfoc.CustomerBuilder;
import org.tfoc.config.MoviePricingConfig;
import org.tfoc.helpers.MoviePricingFaker;
import org.tfoc.models.Customer;
import org.tfoc.models.Movie;
import org.tfoc.models.MovieType;
import org.tfoc.models.Rental;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRentalServiceTest {

    private CustomerRentalService customerRentalService;
    private MoviePricingConfig moviePricingConfig;
    @BeforeEach
    void setUp() {

        moviePricingConfig = new MoviePricingConfig();
        moviePricingConfig.setMoviePrices(MoviePricingFaker.getPricingMap());
        customerRentalService = new CustomerRentalService(moviePricingConfig);
    }

    @Test
    void statementForRegularMovie() {
        Movie movie1 = new Movie("Gone with the Wind", MovieType.REGULAR);
        Rental rental1 = new Rental(movie1, LocalDate.now().minusDays(3)); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tGone with the Wind\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        String statement = customerRentalService.getRentalSummary(customer2);
        assertEquals(expected, statement);
    }

    @Test
    void statementForNewReleaseMovie() {
        Movie movie1 = new Movie("Star Wars", MovieType.NEW_RELEASE);
        Rental rental1 = new Rental(movie1, LocalDate.now().minusDays(3)); // 3 day rental
        Customer customer2 =
                new CustomerBuilder()
                        .withName("Sallie")
                        .withRentals(rental1)
                        .build();
        String expected = "Rental Record for Sallie\n" +
                "\tStar Wars\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        String statement = customerRentalService.getRentalSummary(customer2);
        assertEquals(expected, statement);
    }

    @Test
    void statementForChildrensMovie() {
        Movie movie1 = new Movie("Madagascar", MovieType.CHILDRENS);
        Rental rental1 = new Rental(movie1, LocalDate.now().minusDays(3)); // 3 day rental
        Customer customer2
                = new CustomerBuilder()
                .withName("Sallie")
                .withRentals(rental1)
                .build();
        String expected = "Rental Record for Sallie\n" +
                "\tMadagascar\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        String statement = customerRentalService.getRentalSummary(customer2);
        assertEquals(expected, statement);
    }

    @Test
    void statementForManyMovies() {
        Movie movie1 = new Movie("Madagascar", MovieType.CHILDRENS);
        Rental rental1 = new Rental(movie1, LocalDate.now().minusDays(6)); // 6 day rental
        Movie movie2 = new Movie("Star Wars", MovieType.NEW_RELEASE);
        Rental rental2 = new Rental(movie2, LocalDate.now().minusDays(2)); // 2 day rental
        Movie movie3 = new Movie("Gone with the Wind", MovieType.REGULAR);
        Rental rental3 = new Rental(movie3, LocalDate.now().minusDays(8)); // 8 day rental
        Customer customer1
                = new CustomerBuilder()
                .withName("David")
                .withRentals(rental1, rental2, rental3)
                .build();
        String expected = "Rental Record for David\n" +
                "\tMadagascar\t6.0\n" +
                "\tStar Wars\t6.0\n" +
                "\tGone with the Wind\t11.0\n" +
                "Amount owed is 23.0\n" +
                "You earned 4 frequent renter points";
        String statement = customerRentalService.getRentalSummary(customer1);
        assertEquals(expected, statement);
    }
}