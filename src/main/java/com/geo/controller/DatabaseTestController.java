package com.geo.controller; // Adjust package as needed

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") // This will map the root URL
public class DatabaseTestController {

    @GetMapping
    public String home() {
        return "home"; // This should match a template file name in your resources/templates directory
    }
}