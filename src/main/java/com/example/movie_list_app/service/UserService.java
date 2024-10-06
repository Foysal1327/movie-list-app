package com.example.movie_list_app.service;

import com.example.movie_list_app.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserService {
    private Map<String, User> registeredUsers = new HashMap<>();

    //register new user
    public String registerUser(String email){
        if(registeredUsers.containsKey(email)){
            return "User already registered";
        }
        else{
            User user = new User(email);
            registeredUsers.put(email, user);
            return "User registered successfully";
        }
    }
    // Get user by email
    public User getUserByEmail(String email) {
        return registeredUsers.get(email);
    }
    public Map<String, User> getAllUsers() {
        return registeredUsers;
    }
}
