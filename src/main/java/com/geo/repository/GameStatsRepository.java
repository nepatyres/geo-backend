package com.geo.repository;

import com.geo.model.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameStatsRepository extends JpaRepository<GameStats, Long>{
    List<GameStats> findByUsername(String username);
}