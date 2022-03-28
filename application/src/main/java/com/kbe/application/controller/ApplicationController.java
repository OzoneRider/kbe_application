package com.kbe.application.controller;

import com.kbe.application.models.Product;
import com.kbe.application.models.ProductDetails;
import com.kbe.application.models.externalAPI.GeoCode;
import com.kbe.application.models.storageAPI.DeliveryInformation;
import com.kbe.application.services.ProductDetailService;
import com.kbe.application.services.ProductService;
import com.kbe.application.services.externalAPI.MapService;
import com.kbe.application.services.storageAPI.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
@CommonsLog
@RequiredArgsConstructor
public class ApplicationController {

    private final ProductService productService;
    private final StorageService storageService;
    private final MapService mapService;
    private final ProductDetailService productDetailService;


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
        ProductDetails productDetails = productDetailService.getProductDetails(id);

        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("products/{id}/delivery-information/geo")
    public ResponseEntity<GeoCode> getGeoCode(@PathVariable Integer id){
        DeliveryInformation deliveryInformation = storageService.importDeliveryInformation(id);

        return mapService.getGeoCoordinates(deliveryInformation.getProductLocation());
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

    @DeleteMapping("products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        return productService.deleteProduct(id);
    }

}
