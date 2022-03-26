package com.kbe.application.services;

import com.kbe.application.models.Product;
import com.kbe.application.models.ProductDetails;
import com.kbe.application.models.calculatorAPI.VAT;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import com.kbe.application.services.calculatorAPI.CalculatorService;
import com.kbe.application.services.storageAPI.StorageService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductDetailService {

    private final ProductService productService;

    private final StorageService storageService;

    private final DeliveryDateService deliveryDateService;

    private final CalculatorService calculatorService;

    public ProductDetailService(ProductService productService, StorageService storageService,
                                DeliveryDateService deliveryDateService, CalculatorService calculatorService) {
        this.productService = productService;
        this.storageService = storageService;
        this.deliveryDateService = deliveryDateService;
        this.calculatorService = calculatorService;
    }

    public ProductDetails getProductDetails(int id){
        Product product = productService.getProductById(id);
        DeliveryInformation info = storageService.importDeliveryInformation(id);
        VAT vat = calculatorService.calculateVAT(product);
        LocalDate deliveryDate = deliveryDateService.getDeliveryDate(info.getDeliveryTimeDays());
        return new ProductDetails(product, info, vat.getVatPrice(), deliveryDate);
    }
}
