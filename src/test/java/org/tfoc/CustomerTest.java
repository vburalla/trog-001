package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.tfoc.models.Customer;
import org.tfoc.models.Movie;
import org.tfoc.models.MovieType;
import org.tfoc.models.Rental;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CustomerTest {

    @Test
    void testCustomer() {
        Customer c = new CustomerBuilder().build();
        assertNotNull(c);
    }

    @Test
    void testAddRental() {
        Customer customer2 = new CustomerBuilder().withName("Sallie").build();
        Movie movie1 = new Movie("Gone with the Wind", MovieType.REGULAR);
        Rental rental1 = new Rental(movie1, LocalDate.now().minusDays(3)); // 3 day rental
        customer2.addRental(rental1);
        assertEquals(1, customer2.getRentals().size());
    }

    @Test
    void testGetName() {
        Customer c = new Customer("David");
        assertEquals("David", c.getName());
    }

}