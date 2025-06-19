package com.jaypal.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTest {


    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailTest() {
        emailService.sendEmail("kolijaypal47@gmail.com", "spring-boot-mail-service-test", "Working fine");
    }

}
