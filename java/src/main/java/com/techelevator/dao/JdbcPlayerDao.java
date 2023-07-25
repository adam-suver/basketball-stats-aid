package com.techelevator.dao;

import com.techelevator.model.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


public class JdbcPlayerDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Player> getAllPlayers() {

        return null;
    }
}
