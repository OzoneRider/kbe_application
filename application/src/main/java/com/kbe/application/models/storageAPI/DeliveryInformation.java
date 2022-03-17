package com.kbe.application.models.storageAPI;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInformation {

    private int productId;
    private Location productLocation;
    private String deliveryTime;
    private int amount;
    private double vat;
}
