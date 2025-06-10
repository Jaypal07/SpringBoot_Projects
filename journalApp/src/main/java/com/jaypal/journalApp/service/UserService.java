package com.jaypal.journalApp.service;

import com.jaypal.journalApp.controller.PublicController;
import com.jaypal.journalApp.entity.User;
import com.jaypal.journalApp.repository.UserRepository;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.plaf.PanelUI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private boolean isPasswordEncoded(String password) {
        return password != null && password.matches("^\\$2[aby]\\$.{56}$");
    }


    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveUser(User user) {
        if (!isPasswordEncoded(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }


    public List<User> getAll() {

        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found")));
    }

    public void deleteById(ObjectId id) {

        userRepository.deleteById(id);
    }

    public User findByUserName(String username) {

        return userRepository.findByUsername(username);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public @NonNull String encodePassword(@NonNull String password) {
        return null;
    }


}
