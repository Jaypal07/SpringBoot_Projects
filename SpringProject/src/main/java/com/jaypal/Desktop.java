package com.jaypal;

import org.springframework.stereotype.Component;

@Component
public class Desktop implements Computer{
    @Override
    public void code() {
        System.out.println("Code using Desktop");
    }
}
