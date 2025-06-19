package com.jaypal.journalApp.repository;

import com.jaypal.journalApp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void getUserForSATest() {
        for (User user : userRepository.getUserForSA()) {
            System.out.println(user.getUsername());
        }

    }
}
