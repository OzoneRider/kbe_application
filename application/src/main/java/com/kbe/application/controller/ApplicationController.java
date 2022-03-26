package com.kbe.application.controller;

import com.kbe.application.models.Product;
import com.kbe.application.models.ProductDetails;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/")
@CommonsLog
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
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getProducts();;

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> findByName(@RequestParam("name") String name){
        List<Product> productList = productService.searchByName(name);

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDetails> getProductInformation(@PathVariable("id") Integer id){
        Product product = productService.getProductById(id);
        DeliveryInformation info = storageService.importDeliveryInformation(id);
        VAT vat = calculatorService.calculateVAT(product);
        LocalDate deliveryDate = deliveryDateService.getDeliveryDate(info.getDeliveryTimeDays());
        ProductDetails productDetails = new ProductDetails(product, info, vat.getVatPrice(), deliveryDate);

        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("products/{id}/delivery-information/geo")
    public ResponseEntity<GeoCode> getGeoCode(@PathVariable Integer id){
        DeliveryInformation deliveryInformation = storageService.importDeliveryInformation(id);

        return mapService.getGeoCoordinates(deliveryInformation.getProductLocation());
    }

    @GetMapping("products/export")
    public ResponseEntity<?> exportProductsToCSV(){
        try{
            csvExportService.exportCsvToFolder();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(IOException e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("products")
    public ResponseEntity<?> postProduct(@Valid @RequestBody Product product){
        ResponseEntity<?> response = productService.saveProduct(product);

        return response;
    }

    @PostMapping("products/{id}/delivery-information")
    public ResponseEntity<?> createDeliveryInformation(@Valid @RequestBody DeliveryInformation info,
                                                   @PathVariable("id") Integer id){
        if(id != info.getId())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return storageService.exportDeliveryInformation(info);

    }

    @PutMapping("products/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, @PathVariable("id") Integer id){
        return productService.updateProduct(product);
    }

    @PutMapping("products/{id}/delivery-information")
    public ResponseEntity<?> updateDeliveryInformation(@Valid @RequestBody DeliveryInformation info,
                                                         @PathVariable("id") Integer id){
        if(id != info.getId())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return storageService.updateDeliveryInformation(info);
    }

}
