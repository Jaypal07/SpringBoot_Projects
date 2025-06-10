package com.jaypal.journalApp.service;

import com.jaypal.journalApp.entity.User;
import com.jaypal.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addTest() {
        assertEquals(5, 3 + 2);
    }


    @Disabled
    @Test
    public void findByUsernameTest() {
        User user = userRepository.findByUsername("jp");
        assertFalse(user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2, 10, 12"
    }
    )
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b, "failed for" + expected);
    }

}
