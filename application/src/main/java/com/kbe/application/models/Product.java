package com.kbe.application.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
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
