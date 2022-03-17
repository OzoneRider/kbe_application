package com.kbe.application.models;

import com.kbe.application.models.storageAPI.DeliveryInformation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformation {
    private Product product;
    private DeliveryInformation deliveryInformation;
}
