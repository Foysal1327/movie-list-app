package com.example.movie_list_app.controller;

import com.example.movie_list_app.model.User;
import com.example.movie_list_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam("email") String email) {
        String message = userService.registerUser(email);
        if (message.equals("User registered successfully.")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
    // Get all registered users
    @GetMapping("/all")
    public Map<String, User> getAllUsers() {
        return userService.getAllUsers();
    }
}
