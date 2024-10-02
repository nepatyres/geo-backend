package com.geo.controller;

import com.geo.model.UserLoginDTO;
import com.geo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
      this.authenticationManager = authenticationManager;
      this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        logger.info("Login attempt for user: {}", userLoginDTO.getUsername());
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
            );
            logger.info("Authenticated successful for user: {}", userLoginDTO.getUsername());
            String jwtToken = jwtUtil.generateToken(userLoginDTO.getUsername());
            logger.info("JWT token generated for user: {}", userLoginDTO.getUsername());
            return ResponseEntity.ok().body("{\"token\":\"" + jwtToken + "\"}");
        } catch (AuthenticationException e){
            logger.error("Authentication failed for user: {}", userLoginDTO.getUsername(),e );
            return ResponseEntity.badRequest().body("{\"error\":\"Invalid username or password\"}");
        } catch (Exception e){
            logger.error("Unexpected error during login for user: {}", userLoginDTO.getUsername(), e);
            return ResponseEntity.internalServerError().body("{\"error\": \"An unexpected error occured\"}");
        }
    }
}