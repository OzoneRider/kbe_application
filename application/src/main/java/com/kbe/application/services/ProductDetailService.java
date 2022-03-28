package com.kbe.application.services;

import com.kbe.application.models.Product;
import com.kbe.application.models.ProductDetails;
import com.kbe.application.models.calculatorAPI.VAT;
import com.kbe.application.models.externalAPI.GeoCode;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import com.kbe.application.services.calculatorAPI.CalculatorService;
import com.kbe.application.services.externalAPI.MapService;
import com.kbe.application.services.storageAPI.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProductDetailService {

    private final ProductService productService;
    private final StorageService storageService;
    private final DeliveryDateService deliveryDateService;
    private final CalculatorService calculatorService;
    private final MapService mapService;

    public ProductDetails getProductDetails(int id){
        Product product = productService.getProductById(id);
        DeliveryInformation info = storageService.importDeliveryInformation(id);
        VAT vat = calculatorService.calculateVAT(product);
        LocalDate deliveryDate = deliveryDateService.getDeliveryDate(info.getDeliveryTimeDays());
        GeoCode geoCode = mapService.getGeoCoordinates(info.getProductLocation()).getBody();
        return new ProductDetails(product, info, vat.getVatPrice(), deliveryDate, geoCode);
    }
}
