package com.kbe.application.repository;

import com.kbe.application.models.Product;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final String hashReference = "Product";

    @Resource(name="redisTemplate")
    private HashOperations<String, Integer, Product> hashOperations;

    @Override
    public void saveProduct(Product product) {
        hashOperations.putIfAbsent(hashReference, product.getId(), product);
    }

    @Override
    public void saveAllProducts(Map<Integer, Product> map){
        hashOperations.putAll(hashReference, map);
    }

    @Override
    public Product getProductById(Integer id) {
        return hashOperations.get(hashReference, id);
    }

    @Override
    public Map<Integer, Product> getAllProducts() {
        return hashOperations.entries(hashReference);
    }

    @Override
    public void updateProduct(Product product) {
        hashOperations.put(hashReference, product.getId(), product);
    }

    @Override
    public void deleteProduct(Integer id) {
        hashOperations.delete(hashReference, id);
    }
}
