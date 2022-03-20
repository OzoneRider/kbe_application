package com.kbe.application.exceptions;

import com.kbe.application.models.Product;

public class ProductNotCreatedException extends RuntimeException{

    public ProductNotCreatedException(Product product){
        super(String.format("Product with body\n %s not created", product.toString()));
    }
}
