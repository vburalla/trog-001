package org.tfoc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePricing {

    private Double fixedPrice;
    private Integer includedDaysInPrice;
    private Double pricePerExtraDay;
}
