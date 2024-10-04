package com.geo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name= "game_stats")
public class GameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username is required")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "continent is required")
    @Column(nullable = false)
    private String continent;

    @Min(value = 0, message = "score must be >= 0")
    @Column(nullable = false)
    private int score;

    @Min(value = 0, message = "attempts must be >= 0")
    @Column(nullable = false)
    private int attempts;

    @NotBlank(message = "time is required")
    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public GameStats(){
        this.createdAt = LocalDateTime.now();
    }

    public GameStats(String username, String continent, int score, int attempts, String time){
        this.username = username;
        this.continent = continent;
        this.score = score;
        this.attempts = attempts;
        this.time = time;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getContinent(){
        return continent;
    }

    public void setContinent(String continent){
        this.continent = continent;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getAttempts(){
        return attempts;
    }

    public void setAttempts(int attempts){
        this.attempts = attempts;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}