package com.kbe.application.models.calculatorAPI;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class VAT {

    @NotNull
    @DecimalMin("0.0")
    @Digits(integer = 4, fraction = 2)
    private BigDecimal vatPrice;
}
