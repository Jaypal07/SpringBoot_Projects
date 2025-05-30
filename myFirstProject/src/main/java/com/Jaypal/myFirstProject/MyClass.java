package com.Jaypal.myFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClass {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("abc")
    public String sayABC(){
        return "ABC";
    }

    @Autowired
    private Dog dog;

    @GetMapping("ok")
    public String sayOk(){
        return dog.fun();
    }
}
