package com.jaypal.springdemo;

import com.jaypal.springdemo.model.Alien;
import com.jaypal.springdemo.model.Laptop;
import com.jaypal.springdemo.service.LaptopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);

        LaptopService service = context.getBean(LaptopService.class);

        Laptop laptop = context.getBean(Laptop.class);
        service.addLaptop(laptop);
        System.out.println(service.isLaptopGoodForCoding(laptop));

        
    }

}
