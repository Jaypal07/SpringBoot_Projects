package com.jaypal.Product.controller;

import com.jaypal.Product.entity.Product;
import com.jaypal.Product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class Products {

    private final ProductService productService;

    public Products(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return product;
    }

    @GetMapping("/all-products")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
        return "Product with id: " + id + " has been deleted";
    }

    @PutMapping("/update/{id}")
    public Product updateProductById(@PathVariable String id, @RequestBody Product product) {
        productService.updateProductById(id, product);
        return product;
    }
}
