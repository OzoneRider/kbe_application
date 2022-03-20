package com.kbe.application.controller;

import com.kbe.application.exceptions.*;
import com.kbe.application.models.Product;
import com.kbe.application.models.ProductInformation;
import com.kbe.application.models.calculatorAPI.VAT;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import com.kbe.application.services.ProductService;
import com.kbe.application.services.calculatorAPI.CalculatorService;
import com.kbe.application.services.csv.CSVExportService;
import com.kbe.application.services.storageAPI.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class ApplicationController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CalculatorService calculatorService;
    @Autowired
    ProductService productService;
    @Autowired
    StorageService storageService;
    @Autowired
    CSVExportService csvExportService;

    @GetMapping("products")
    public List<Product> getAllProducts(){
        List<Product> products = productService.getProducts();

        if(products.isEmpty())
            throw new NoProductDataException();

        return products;
    }

    @GetMapping("products/{id}")
    public ProductInformation getProductInformation(@PathVariable("id") int id){
        Product product = productService.getProductById(id);
        DeliveryInformation deliveryInformation = storageService.importDeliveryInformation(id);
        VAT vat = calculatorService.calculateVAT(product);

        if(product == null)
            throw new ProductNotFoundException(id);
        else if(deliveryInformation == null)
            throw new DeliveryInformationNotFoundException(id);

        return new ProductInformation(product, deliveryInformation, vat.getVatPrice());
    }

    @PostMapping("products")
    public Product postProduct(@Valid @RequestBody Product product){

        productService.saveProduct(product);
        Product temp = productService.getProductById(product.getProductId());

        if(temp == null && !temp.equals(product))
            throw new ProductNotCreatedException(product);

        csvExportService.exportCsvToFolder();

        return product;
    }

    @PostMapping("products/{id}/delivery-information")
    public DeliveryInformation deliveryInformation(@Valid @RequestBody DeliveryInformation deliveryInformation,
                                                   @PathVariable("id") Integer id){

        if(id != deliveryInformation.getProductId())
            throw new ProductNotFoundException(deliveryInformation.getProductId());

        DeliveryInformation temp = storageService.exportDeliveryInformation(deliveryInformation);

        if(temp == null)
            throw new DeliveryInformationNotCreatedException(deliveryInformation);

        return deliveryInformation;
    }

    @PutMapping("products/{id}")
    public Product updateProduct(@Valid @RequestBody Product product, @PathVariable("id") Integer id){

        if(id != product.getProductId())
            throw new ProductNotFoundException(product.getProductId());

        productService.updateProduct(product);

        return product;
    }

    @PutMapping("products/{id}/delivery-information")
    public DeliveryInformation updateDeliveryInformation(@Valid @RequestBody DeliveryInformation deliveryInformation,
                                                         @PathVariable("id") Integer id){

        if(id != deliveryInformation.getProductId())
            throw new ProductNotFoundException(deliveryInformation.getProductId());

        DeliveryInformation temp = storageService.updateDeliveryInformation(id, deliveryInformation);

        if(temp == null)
            throw new DeliveryInformationNotFoundException(id);

        return temp;
    }
    
}
