package com.kbe.application.repository;

import com.kbe.application.models.Product;

import java.util.Map;

public interface ProductRepository {
    void saveProduct(Product product);
    void saveAllProducts(Map<Integer, Product> map);
    Product getProductById(Integer id);
    Map<Integer, Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Integer id);
}
