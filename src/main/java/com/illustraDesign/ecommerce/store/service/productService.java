package com.illustraDesign.ecommerce.store.service;

import com.illustraDesign.ecommerce.store.model.product;
import com.illustraDesign.ecommerce.store.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    @Autowired
    private productRepository productRepository;

    public void saveProduct(product product) {
        productRepository.save(product);
    }

    public List<product> getAllProducts() {
        return productRepository.findAll();
    }

    public product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
