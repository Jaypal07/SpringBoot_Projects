package com.jaypal.config;

import com.jaypal.Alien;
import com.jaypal.Computer;
import com.jaypal.Desktop;
import com.jaypal.Laptop;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.jaypal")
public class AppConfig {

//    @Bean
//    public Alien alien( Computer computer){ //@Qualifier("laptop")
//        Alien obj = new Alien();
//        obj.setCom(computer);
//        return obj;
//    }
//
//    @Bean
//    @Primary
//    public Desktop desktop(){
//        return new Desktop();
//    }
//
//    @Bean
//    public Laptop laptop(){
//        return new Laptop();
//    }

}
