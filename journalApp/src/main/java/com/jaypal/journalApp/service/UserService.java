package com.jaypal.journalApp.service;

import com.jaypal.journalApp.entity.User;
import com.jaypal.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveNewUser(User user) {
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

}
