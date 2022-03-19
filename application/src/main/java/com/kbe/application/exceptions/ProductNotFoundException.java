package com.kbe.application.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Integer id){
        super(String.format("Product with Id %d not found", id));
    }
}
