package com.jaypal.springdemo.service;

import com.jaypal.springdemo.Repository.LaptopRepository;
import com.jaypal.springdemo.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;


    public void addLaptop(Laptop laptop){
        laptopRepository.save(laptop);
    }

    public boolean isLaptopGoodForCoding(Laptop laptop){
        return true;
    }
}
