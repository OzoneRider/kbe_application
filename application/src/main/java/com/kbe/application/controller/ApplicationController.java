package com.kbe.application.controller;

import com.kbe.application.exceptions.*;
import com.kbe.application.models.Product;
import com.kbe.application.models.ProductInformation;
import com.kbe.application.models.calculatorAPI.VAT;
import com.kbe.application.models.externalAPI.GeoCode;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import com.kbe.application.services.DeliveryDateService;
import com.kbe.application.services.ProductService;
import com.kbe.application.services.calculatorAPI.CalculatorService;
import com.kbe.application.services.csv.CSVExportService;
import com.kbe.application.services.externalAPI.MapService;
import com.kbe.application.services.storageAPI.StorageService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/")
public class ApplicationController {

    @Autowired
    CalculatorService calculatorService;
    @Autowired
    ProductService productService;
    @Autowired
    StorageService storageService;
    @Autowired
    CSVExportService csvExportService;
    @Autowired
    MapService mapService;
    @Autowired
    DeliveryDateService deliveryDateService;

    @GetMapping("products")
    public List<Product> getAllProducts(){
        List<Product> products = productService.getProducts();

        if(products.isEmpty())
            throw new NoProductDataException();


        return products;
    }

    @GetMapping("products/{id}")
    public ProductInformation getProductInformation(@PathVariable("id") Integer id){
        Product product = productService.getProductById(id);
        DeliveryInformation info = storageService.importDeliveryInformation(id);
        VAT vat = calculatorService.calculateVAT(product);
        LocalDate deliveryDate = deliveryDateService.getDeliveryDate(info.getDeliveryTimeDays());

        if(product == null)
            throw new ProductNotFoundException(id);

        else if(info == null)
            throw new DeliveryInformationNotFoundException(id);


        return new ProductInformation(product, info,
                calculatorService.calculateVAT(product).getVatPrice(),
                deliveryDateService.getDeliveryDate(info.getDeliveryTimeDays()));
    }

    @GetMapping("products/{id}/delivery-information/geo")
    public GeoCode getGeoCode(@PathVariable Integer id){
        DeliveryInformation deliveryInformation = storageService.importDeliveryInformation(id);
        return mapService.getGeoCoordinates(deliveryInformation.getProductLocation());
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
    public DeliveryInformation deliveryInformation(@Valid @RequestBody DeliveryInformation info,
                                                   @PathVariable("id") Integer id){

        if(id != info.getProductId())
            throw new ProductNotFoundException(info.getProductId());


        DeliveryInformation temp = storageService.exportDeliveryInformation(info);

        if(temp == null)
            throw new DeliveryInformationNotCreatedException(info);

        return info;
    }

    @PutMapping("products/{id}")
    public Product updateProduct(@Valid @RequestBody Product product, @PathVariable("id") Integer id){

        if(id != product.getProductId())
            throw new ProductNotFoundException(product.getProductId());


        productService.updateProduct(product);

        return product;
    }

    @PutMapping("products/{id}/delivery-information")
    public DeliveryInformation updateDeliveryInformation(@Valid @RequestBody DeliveryInformation info,
                                                         @PathVariable("id") Integer id){

        if(id != info.getProductId())
            throw new ProductNotFoundException(info.getProductId());


        DeliveryInformation temp = storageService.updateDeliveryInformation(id, info);

        if(temp == null)
            throw new DeliveryInformationNotFoundException(id);


        return temp;
    }

}
