package com.techelevator.controller;

import com.techelevator.dao.PlayerDao;
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

    @Autowired
    private PlayerDao playerDao;

    @GetMapping(value = "/all-players")
    public Map<Integer, Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    @GetMapping(value = "/player-list")
    public List<Player> getPlayerList() {
        return playerDao.getPlayersFromDatabase();
    }

}
