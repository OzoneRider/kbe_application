package com.kbe.application.controller;

import com.kbe.application.models.Product;
import com.kbe.application.models.ProductInformation;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import com.kbe.application.services.ProductService;
import com.kbe.application.services.calculatorAPI.CalculatorService;
import com.kbe.application.services.csv.CSVExportService;
import com.kbe.application.services.storageAPI.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class ApplicationController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CalculatorService calculatorService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;
    @Autowired
    CSVExportService csvExportService;

    @GetMapping("products")
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("products/{id}")
    public ProductInformation getProductInformation(@PathVariable("id") int id){
        Product product = productService.getProductById(id);
        DeliveryInformation deliveryInformation = storageService.importDeliveryInformation(id);
        return null;
    }


}
