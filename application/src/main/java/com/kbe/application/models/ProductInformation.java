package com.kbe.application.models;

import com.kbe.application.models.storageAPI.DeliveryInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformation {
    private Product product;
    private DeliveryInformation deliveryInformation;
}
