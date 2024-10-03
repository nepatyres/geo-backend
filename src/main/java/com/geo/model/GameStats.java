package com.geo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "game_stats")
public class GameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String continent;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int attempts;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public GameStats(){
        this.createdAt = LocalDateTime.now();
    }

    public GameStats(String username, String continent, int score, int attempts, int time){
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

    public int getTime(){
        return time;
    }

    public void setTime(int time){
        this.time = time;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}