package com.jaypal.Product.service;

import com.jaypal.Product.entity.Product;
import com.jaypal.Product.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final productRepository productRepository;

    @Autowired
    public ProductService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    public void updateProductById(String id, Product product) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.save(product);
        }else {
            System.out.println("Product not found");
        }
    }
}
