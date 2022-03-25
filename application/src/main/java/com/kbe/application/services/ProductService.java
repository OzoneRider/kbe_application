package com.kbe.application.services;

import com.kbe.application.exceptions.NoProductDataException;
import com.kbe.application.exceptions.ProductNotFoundException;
import com.kbe.application.models.Product;
import com.kbe.application.repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Product getProductById(Integer id) throws ProductNotFoundException {
        Product product = productRepository.getProductById(id);
        if(product == null)
            throw new ProductNotFoundException(id);
        return product;
    }

    public ResponseEntity<?> saveProduct(Product product) throws ProductNotFoundException{
        productRepository.saveProduct(product);

        if(productRepository.getProductById(product.getProductId()) == null)
            throw new ProductNotFoundException(product.getProductId());

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateProduct(Product product) throws ProductNotFoundException{
        productRepository.updateProduct(product);

        if(productRepository.getProductById(product.getProductId()) == null)
            throw new ProductNotFoundException(product.getProductId());

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteProduct(Integer id){
        productRepository.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
