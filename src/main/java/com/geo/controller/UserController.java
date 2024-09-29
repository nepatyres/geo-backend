package com.geo.controller;

import com.geo.model.User;
import com.geo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://geo-lilac-one.vercel.app", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Add validation logic here if needed
        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/test")
    public String test() {
        User user = new User();
        user.setName("Test User");
        userRepository.save(user);
        return "user saved!";
    }
}