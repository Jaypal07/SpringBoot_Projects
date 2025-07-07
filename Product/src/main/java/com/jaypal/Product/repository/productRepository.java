package com.jaypal.Product.repository;

import com.jaypal.Product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface productRepository extends MongoRepository<Product, String> {

}
