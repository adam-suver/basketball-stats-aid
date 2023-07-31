package com.techelevator.controller;

import com.techelevator.model.Player;
import com.techelevator.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/all-players")
    public Map<Integer, Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/points/{id}")
    public Map<LocalDate, Integer> getPlayerPoints(@PathVariable int id) {
        return playerService.getPlayerPoints(id);
    }
}
