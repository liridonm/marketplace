package com.patela.marketplace.controller;

import com.patela.marketplace.model.domain.User;
import com.patela.marketplace.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("v1/user/read")
    public List<User> getAllUsers() {
        return this.userService.readAll();
    }

    @PostMapping("/p1/user/create")
    public User create(@RequestBody @Valid User user) {
        return this.userService.createAccount(user);
    }
}
