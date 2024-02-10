package org.tfoc;

import org.tfoc.models.Customer;
import org.tfoc.models.Rental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerBuilder {

    public static final String NAME = "Gregroire";
    private String name = NAME;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer build() {
        Customer result = new Customer(name);
        for (Rental rental : rentals) {
            result.addRental(rental);
        }
        return result;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withRentals(Rental... rentals) {
        Collections.addAll(this.rentals, rentals);
        return this;
    }

}
