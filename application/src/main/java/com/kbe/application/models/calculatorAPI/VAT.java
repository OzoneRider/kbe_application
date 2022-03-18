package com.kbe.application.models.calculatorAPI;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class VAT {

    private BigDecimal vatPrice;
}
