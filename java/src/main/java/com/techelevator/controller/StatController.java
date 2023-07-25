package com.techelevator.controller;


import com.techelevator.model.Game;
import com.techelevator.service.StatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StatController {

    private StatService statService;

    @GetMapping(value = "/{id}/{category}")
    public List<Game> getGameStats(@PathVariable int id, @PathVariable String category) {
        return statService.getSearchResults(id, category);
    }
}
