package com.example.movie_list_app.controller;

import com.example.movie_list_app.model.User;
import com.example.movie_list_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_success() {
        String email = "user@example.com";
        when(userService.registerUser(email)).thenReturn("User registered successfully.");

        ResponseEntity<String> response = userController.registerUser(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully.", response.getBody());
        verify(userService, times(1)).registerUser(email);
    }

    @Test
    void testRegisterUser_failure() {
        String email = "user@example.com";
        when(userService.registerUser(email)).thenReturn("User already registered.");

        ResponseEntity<String> response = userController.registerUser(email);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User already registered.", response.getBody());
        verify(userService, times(1)).registerUser(email);
    }

    @Test
    void testGetAllUsers() {
        Map<String, User> users = new HashMap<>();
        users.put("user@example.com", new User("user@example.com"));
        when(userService.getAllUsers()).thenReturn(users);

        Map<String, User> response = userController.getAllUsers();

        assertEquals(1, response.size());
        assertEquals("user@example.com", response.get("user@example.com").getEmail());
        verify(userService, times(1)).getAllUsers();
    }
}
