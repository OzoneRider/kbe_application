package com.kbe.application.controller;

import com.kbe.application.services.ProductService;
import com.kbe.application.services.calculatorAPI.CalculatorService;
import com.kbe.application.services.storageAPI.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApplicationController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CalculatorService calculatorService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;
}
