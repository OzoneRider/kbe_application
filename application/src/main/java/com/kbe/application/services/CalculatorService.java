package com.kbe.application.services;

import com.kbe.application.models.Product;
import com.kbe.application.models.calculatorAPI.VAT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CalculatorService {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://kbe-calculator/vat";

    public VAT calculateVAT(Product product){
        VAT vat = restTemplate.getForObject(URL+"?price="+product.getPriceEuro()+"/", VAT.class);
        return vat;
    }
}
