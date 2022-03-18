package com.kbe.application.models;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Min(0)
    private int productId;
    @NotNull
    private String name;
    @DecimalMin("10.0")
    @DecimalMax("5000.0")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal priceEuro;
    @NotNull
    private String manufacturer;
    @DecimalMin("10.0")
    @DecimalMax("50.0")
    @Digits(integer = 2, fraction = 1)
    private BigDecimal displaySizeInches;
    @NotNull
    private String color;
    @Min(30)
    @Max(360)
    private int refreshRateHz;
    @DecimalMin("0.25")
    @DecimalMax("15.0")
    @Digits(integer = 2, fraction = 3)
    private BigDecimal weightKg;
    @Min(1)
    @Max(10)
    private int reactionTimeMs;
    @NotNull
    private String displayInterface;
    @NotNull
    private String resolution;
}
