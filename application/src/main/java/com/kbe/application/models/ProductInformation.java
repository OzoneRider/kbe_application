package com.kbe.application.models;

import com.kbe.application.models.storageAPI.DeliveryInformation;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformation {

    @NotNull
    private Product product;
    @NotNull
    private DeliveryInformation deliveryInformation;
}
