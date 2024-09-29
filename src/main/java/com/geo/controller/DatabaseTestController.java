package com.geo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DatabaseTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test-connection")
    public List<Map<String, Object>> testConnection() {
        String sql = "SELECT * FROM pg_stat_user_tables";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
}