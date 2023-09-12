package com.techelevator.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.techelevator.model.DualStatDto;
import com.techelevator.model.Game;
import com.techelevator.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class StatController {

    @Autowired
    private StatService statService;

    @GetMapping(value = "/points/{id}")
    public Map<LocalDate, Integer> getPlayerPoints(@PathVariable int id) {
        return statService.getPlayerSingleStat(id, "points");
    }

    @GetMapping(value = "/assists/{id}")
    public Map<LocalDate, Integer> getPlayerAssists(@PathVariable int id) {
        return statService.getPlayerSingleStat(id, "assists");
    }

    @GetMapping(value = "/blocks/{id}")
    public  Map<LocalDate, Integer> getPlayerBlocks(@PathVariable int id) {
        return statService.getPlayerSingleStat(id, "blocks");
    }

    @GetMapping(value = "/rebounds/{id}")
    public  Map<LocalDate, Integer> getPlayerRebounds(@PathVariable int id) {
        return statService.getPlayerSingleStat(id, "rebounds");
    }

    @GetMapping(value = "/steals/{id}")
    public Map<LocalDate, Integer> getPlayerSteals(@PathVariable int id) {
        return statService.getPlayerSingleStat(id, "steals");
    }

    @GetMapping(value = "/field-goals/{id}")
    public Map<LocalDate, DualStatDto> getPlayerFieldGoals(@PathVariable int id) {
        return statService.getPlayerDualStat(id, "field-goals");
    }

    @GetMapping(value = "/threes/{id}")
    public Map<LocalDate, DualStatDto> getPlayerThrees(@PathVariable int id) {
        return statService.getPlayerDualStat(id, "threes");
    }

    @GetMapping(value = "/free-throws/{id}")
    public Map<LocalDate, DualStatDto> getPlayerFreeThrows(@PathVariable int id) {
        return statService.getPlayerDualStat(id, "free-throws");
    }

}
