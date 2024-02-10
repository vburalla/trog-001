package org.tfoc.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Setter;
import org.tfoc.models.MoviePricing;
import org.tfoc.models.MovieType;

import java.util.Map;

@Configuration
@ConfigurationProperties("movies.prices")
@Getter
@Setter
public class MoviePricingConfig {

    private Map<String, MoviePricing> moviePrices;

    public MoviePricing getMoviePrice(MovieType movieType) {

        return moviePrices.get(movieType.toString());
    }
}
