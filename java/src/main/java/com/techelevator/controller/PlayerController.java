package com.techelevator.controller;

import com.techelevator.model.Player;
import com.techelevator.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/all-players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}
