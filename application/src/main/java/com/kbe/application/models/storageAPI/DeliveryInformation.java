package com.kbe.application.models.storageAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInformation {

    private int productId;
    private Location productLocation;
    private String deliveryTime;
    private int amount;
    private double vat;
}
