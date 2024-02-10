package org.tfoc.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        this.rentals.add(arg);
    }

}
