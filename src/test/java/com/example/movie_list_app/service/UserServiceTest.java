package com.example.movie_list_app.service;

import com.example.movie_list_app.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();  // Initialize the UserService before each test
    }

    @Test
    public void testRegisterUserSuccessfully() {
        // Test registering a new user
        String email = "test@example.com";
        String result = userService.registerUser(email);
        assertEquals("User registered successfully", result);

        // Verify that the user has been added
        User user = userService.getUserByEmail(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testRegisterUserAlreadyExists() {
        // First register the user
        String email = "existing@example.com";
        userService.registerUser(email);

        // Try to register the same user again
        String result = userService.registerUser(email);
        assertEquals("User already registered", result);

        // Verify that only one user exists with that email
        User user = userService.getUserByEmail(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetUserByEmail() {
        String email = "findme@example.com";
        userService.registerUser(email);

        User user = userService.getUserByEmail(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testGetAllUsers() {
        // Register multiple users
        String email1 = "user1@example.com";
        String email2 = "user2@example.com";

        userService.registerUser(email1);
        userService.registerUser(email2);

        // Get all registered users
        Map<String, User> users = userService.getAllUsers();
        assertEquals(2, users.size());  // Check if two users were registered
        assertTrue(users.containsKey(email1));
        assertTrue(users.containsKey(email2));
    }
}
