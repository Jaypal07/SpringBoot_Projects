package com.jaypal.springdemo.model;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer {
    @Override
    public void code() {
        System.out.println("Code using Desktop");
    }
}
