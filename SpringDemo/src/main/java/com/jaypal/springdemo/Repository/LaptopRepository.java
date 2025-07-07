package com.jaypal.springdemo.Repository;

import com.jaypal.springdemo.model.Laptop;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopRepository {

    public void save(Laptop laptop){
        System.out.println("save Laptop");
    }
}
