package com.patela.marketplace.service;

import com.patela.marketplace.model.domain.User;
import com.patela.marketplace.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User readByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    public User readByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public User createAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public List<User> readAll() {
        return this.userRepository.findAll();
    }


}
