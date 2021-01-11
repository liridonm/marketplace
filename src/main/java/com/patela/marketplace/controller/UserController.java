package com.patela.marketplace.controller;


import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/read")
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve users!")
    @Operation(summary = "Delete user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseWrapper> readAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved users!", users));
    }

    @DeleteMapping("/delete/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete users!")
    @Operation(summary = "Delete user by id")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseWrapper> delete(@PathVariable("id") Integer id) throws ServiceException {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully delete user!"));
    }
}
