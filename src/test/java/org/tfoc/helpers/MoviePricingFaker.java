package org.tfoc.helpers;

import lombok.experimental.UtilityClass;
import org.tfoc.models.MoviePricing;
import org.tfoc.models.MovieType;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class MoviePricingFaker {



    public Map<String, MoviePricing> getPricingMap() {

        Map<String, MoviePricing> pricingMap = new HashMap<>();
        pricingMap.put(MovieType.REGULAR.toString(), new MoviePricing(2D, 2, 1.5));
        pricingMap.put(MovieType.NEW_RELEASE.toString(), new MoviePricing(0D, 0, 3D));
        pricingMap.put(MovieType.CHILDRENS.toString(), new MoviePricing(1.5, 3, 1.5));

        return pricingMap;
    }
}
