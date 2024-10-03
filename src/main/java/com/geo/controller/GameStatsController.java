package com.geo.controller;

import com.geo.model.GameStats;
import com.geo.repository.GameStatsRepository;
import com.geo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gamestats")
public class GameStatsController {

    private static final Logger logger = LoggerFactory.getLogger(GameStatsController.class);

    @Autowired
    private GameStatsRepository gameStatsRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> saveGameStats(@RequestBody GameStats gameStats, @RequestHeader("Authorization") String token){
        try{
            String username = jwtUtil.extractUsername(token.substring(7));
            gameStats.setUsername(username);
            GameStats savedStats = gameStatsRepository.save(gameStats);
            logger.info("Game stats saved for user: {}", username);
            return ResponseEntity.ok(savedStats);
        } catch(Exception e){
            logger.error("Error saving game stats", e);
            return ResponseEntity.internalServerError().body("Error saving game stats");
        }
    }

    @GetMapping
    public ResponseEntity<?> getGameStats(@RequestHeader("Authorization") String token){
        try{
            String username = jwtUtil.extractUsername(token.substring(7));
            return ResponseEntity.ok(gameStatsRepository.findByUsername(username));
        } catch(Exception e){
            logger.error("Error retrieving game stats", e);
                return ResponseEntity.internalServerError().body("Error retrieving game stats");
        }
    }
}