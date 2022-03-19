package com.kbe.application.exceptions;

public class DeliveryInformationNotFoundException extends RuntimeException{

    public DeliveryInformationNotFoundException(Integer id){
        super(String.format("Delivery Information for Product with Id %d not found", id));
    }
}
