package com.orderaxis.service;

import com.orderaxis.dto.LoginRequest;
import com.orderaxis.dto.RegisterRequest;
import com.orderaxis.entity.User;
import com.orderaxis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Map<String, String> register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Store password as plain text
        user.setRole(request.getRole().toUpperCase());
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return response;
    }

    public Map<String, String> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(request.getPassword())) { // Compare plain text passwords
            throw new RuntimeException("Invalid password");
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "User logged in successfully");
        return response;
    }
}