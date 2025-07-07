package com.jaypal;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {


    public Laptop() {
//        System.out.println("Laptop Constructor");
    }
    @Override
    public void code(){
        System.out.println("Code using Laptop");
    }


}

