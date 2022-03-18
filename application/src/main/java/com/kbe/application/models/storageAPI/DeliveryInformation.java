package com.kbe.application.models.storageAPI;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInformation {

    @Min(0)
    private int productId;
    @NotNull
    private Location productLocation;
    @Min(1)
    @Max(100)
    private int deliveryTimeDays;
    @Min(1)
    @Max(3)
    private int amount;
}
