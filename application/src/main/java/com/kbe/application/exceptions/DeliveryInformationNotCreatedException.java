package com.kbe.application.exceptions;


import com.kbe.application.models.storageAPI.DeliveryInformation;

public class DeliveryInformationNotCreatedException extends RuntimeException{

    public DeliveryInformationNotCreatedException(DeliveryInformation deliveryInformation){
        super(String.format("DeliveryInformation with body\n %s not created", deliveryInformation.toString()));
    }
}
