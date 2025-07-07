package com.jaypal;

import com.jaypal.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
//        ApplicationContext  context = new ClassPathXmlApplicationContext("spring.xml");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Desktop desktop = context.getBean(Desktop.class);
//        desktop.code();
        Alien alien = context.getBean(Alien.class);
        alien.code();

    }

}
