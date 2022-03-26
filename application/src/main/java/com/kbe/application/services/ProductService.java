package com.kbe.application.services;

import com.kbe.application.exceptions.NoProductDataException;
import com.kbe.application.exceptions.ProductNotFoundException;
import com.kbe.application.models.Product;
import com.kbe.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts(){
        Map<Integer, Product> map = productRepository.getAllProducts();
        List products = map.values().stream().collect(Collectors.toList());

        if(products.isEmpty())
            throw new NoProductDataException();
        return products;
    }

    public Product getProductById(Integer id){
        Product product = productRepository.getProductById(id);
        if(product == null)
            throw new ProductNotFoundException(id);
        return product;
    }

    public List<Product> searchByName(String name){
        List<Product> products = getProducts();
        List<Product> productsFiltered = new ArrayList<>();
        for(Product product : products){
            if(name.equals(product.getName()))
                productsFiltered.add(product);
        }

        return productsFiltered;
    }

    public ResponseEntity<?> saveProduct(Product product){
        productRepository.saveProduct(product);

        if(productRepository.getProductById(product.getId()) == null)
            throw new ProductNotFoundException(product.getId());

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateProduct(Product product){
        productRepository.updateProduct(product);

        if(productRepository.getProductById(product.getId()) == null)
            throw new ProductNotFoundException(product.getId());

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteProduct(Integer id){
        productRepository.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
