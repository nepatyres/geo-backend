package com.geo.controller;

import com.geo.model.GameStats;
import com.geo.repository.GameStatsRepository;
import com.geo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gamestats")
public class GameStatsController {

    private static final Logger logger = LoggerFactory.getLogger(GameStatsController.class);

    @Autowired
    private GameStatsRepository gameStatsRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> saveGameStats(@RequestBody GameStats gameStats, @RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing Authorization header");
            }

            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);

            if (username != null && jwtUtil.validateToken(token, username)) {
                if (!username.equals(gameStats.getUsername())) {
                    logger.warn("Attempt to save game stats for a different user");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot save stats for a different user");
                }
                GameStats savedStats = gameStatsRepository.save(gameStats);
                logger.info("Game stats saved for user: {}", username);
                return ResponseEntity.ok(savedStats);
            } else {
                logger.warn("Invalid token for user: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch(Exception e) {
            logger.error("Error saving game stats", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving game stats: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getGameStats(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtUtil.extractUsername(token.substring(7)); // Remove "Bearer " prefix
            if (username != null && !username.equals("anonymousUser")) {
                List<GameStats> gameStatsList = gameStatsRepository.findByUsername(username);

                if (!gameStatsList.isEmpty()) {
                    return ResponseEntity.ok(gameStatsList); // Return all game stats
                } else {
                    logger.info("No game stats found for user: {}", username);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No game stats available for this user.");
                }
            } else {
                logger.warn("Attempt to retrieve game stats for unauthenticated user");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }
        } catch (Exception e) {
            logger.error("Error retrieving game stats", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving game stats");
        }
    }
}