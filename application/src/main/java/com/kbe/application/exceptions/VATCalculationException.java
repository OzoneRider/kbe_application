package com.kbe.application.exceptions;

import java.math.BigDecimal;

public class VATCalculationException extends RuntimeException{

    public VATCalculationException(BigDecimal price){
        super(String.format("Vat for price %s could not be calculated", price.toString()));
    }
}
