package com.kbe.application.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private int productId;
    private String name;
    private double priceEuro;
    private String manufacturer;
    private double displaySizeInches;
    private String color;
    private int refreshRateHz;
    private double weightKg;
    private int reactionTimeMs;
    private String displayInterface;
    private String resolution;
}
