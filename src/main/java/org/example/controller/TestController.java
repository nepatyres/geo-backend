package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public String test(){
        User user = new User();
        user.setName("Test User");
        userRepository.save(user);
        return "user saved!";
    }
}