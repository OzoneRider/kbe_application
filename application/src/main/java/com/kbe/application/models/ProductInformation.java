package com.kbe.application.models;

import com.kbe.application.models.storageAPI.DeliveryInformation;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformation {

    @NotNull
    private Product product;

    @NotNull
    private DeliveryInformation deliveryInformation;

    @DecimalMin("0.0")
    private BigDecimal vat;
}
