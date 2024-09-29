package com.geo.controller;

import com.geo.model.User;
import com.geo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="https://geo-lilac-one.vercel.app/", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @CrossOrigin(origins = "https://geo-lilac-one.vercel.app/")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        //validation logic will be here:
        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/test")
    @CrossOrigin(origins = "https://geo-lilac-one.vercel.app/")
    public String test(){
        User user = new User();
        user.setName("Test User");
        userRepository.save(user);
        return "user saved!";
    }
}