package com.kbe.application.services.calculatorAPI;

import com.kbe.application.exceptions.VATCalculationException;
import com.kbe.application.models.Product;
import com.kbe.application.models.calculatorAPI.VAT;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final RestTemplate restTemplate;

    private final String URL = "http://localhost:4441/vat";

    public VAT calculateVAT(Product product) throws VATCalculationException {
        VAT vat = restTemplate.getForObject(URL+"?price="+ product.getPriceEuro(), VAT.class);
        if(vat == null)
            throw new VATCalculationException(product.getPriceEuro());

        return vat;
    }
}
