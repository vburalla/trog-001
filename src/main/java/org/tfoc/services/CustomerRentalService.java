package org.tfoc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tfoc.config.MoviePricingConfig;
import org.tfoc.models.Customer;
import org.tfoc.models.MoviePricing;
import org.tfoc.models.MovieType;
import org.tfoc.models.Rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CustomerRentalService {

    private final MoviePricingConfig moviePriceConfig;
    private static final MovieType EXTRA_POINT_MOVIE_TYPE = MovieType.NEW_RELEASE;
    private static final String TAB = "\t";
    private static final String NEWLINE = "\n";

    public String getRentalSummary(Customer customer) {

        LocalDate now = LocalDate.now();
        Integer frequentRenterPoints = 0;
        double totalAmount = 0;
        StringBuilder sb = new StringBuilder("Rental Record for ").append(customer.getName()).append(NEWLINE);

        for(Rental rental : customer.getRentals()) {
            long daysRented = getDaysRented(now, rental.getRentalDate());

            double rentalAmount = getMovieRentalAmount(rental, daysRented);
            frequentRenterPoints+=getRentalPoints(rental, daysRented);
            sb.append(TAB).append(rental.getMovie().getTitle()).append(TAB).append(rentalAmount).append(NEWLINE);
            totalAmount+=rentalAmount;
        }

        sb.append("Amount owed is ").append(totalAmount).append(NEWLINE);
        sb.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

        return sb.toString();
    }

    private double getMovieRentalAmount(Rental rental, long days) {

        MoviePricing moviePricing = moviePriceConfig.getMoviePrice(rental.getMovie().getMovieType());

        return moviePricing.getFixedPrice() + (days - moviePricing.getIncludedDaysInPrice()) * moviePricing.getPricePerExtraDay();
    }

    private long getDaysRented(LocalDate currentDate, LocalDate rentalDate) {

        return ChronoUnit.DAYS.between(rentalDate, currentDate);
    }

    private int getRentalPoints(Rental rental, long days) {

        int points = 1;
        if(days > 1 && rental.getMovie().getMovieType().equals(EXTRA_POINT_MOVIE_TYPE))
            points++;
        return points;
    }
}
