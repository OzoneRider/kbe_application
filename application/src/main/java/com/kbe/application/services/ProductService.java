package com.kbe.application.services;

import com.kbe.application.models.Product;
import com.kbe.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        Map<Integer, Product> map = productRepository.getAllProducts();
        List products = map.values().stream().collect(Collectors.toList());
        return products;
    }

    public Product getProductById(Integer id){
        return productRepository.getProductById(id);
    }

    public void saveProduct(Product product){
        productRepository.saveProduct(product);
    }

    public void updateProduct(Product product){
        productRepository.updateProduct(product);
    }

    public void deleteProduct(Integer id){
        productRepository.deleteProduct(id);
    }
}
