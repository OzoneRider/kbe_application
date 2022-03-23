package com.kbe.application.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeliveryDateService {

    public LocalDate getDeliveryDate(int days){
        LocalDate today = LocalDate.now();
        return today.plusDays(days);
    }

}
