package com.jaypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Alien {

    private int age;

    @Autowired
    @Qualifier("laptop")
    private Computer com;


    public Alien(){
        System.out.println("Alien");
    }


    public void code(){
        com.code();
    }

    public int getAge() {
        System.out.println("Setter Called");
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getCom() {
        return com;
    }

    public void setCom(Computer com) {
        this.com = com;
    }
}
