package com.kbe.application.models;

import com.kbe.application.models.externalAPI.GeoCode;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {

    @NotNull
    private Product product;

    @NotNull
    private DeliveryInformation deliveryInformation;

    @DecimalMin("0.0")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal vat;

    @NotNull
    private LocalDate deliveryDate;

    private GeoCode geocode;
}
