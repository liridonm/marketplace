package com.patela.marketplace.service;

import com.patela.marketplace.model.User;
import com.patela.marketplace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    public User readByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public User createOrUpdate(User user) {
        return this.userRepository.save(user);
    }

    public List<User> readAll() {
        return this.userRepository.findAll();
    }

}
