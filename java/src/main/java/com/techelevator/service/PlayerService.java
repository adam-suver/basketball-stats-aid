package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.PlayerDao;
import com.techelevator.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@CrossOrigin
public class PlayerService {

    @Autowired
    private final PlayerDao playerDao;

    private String apiURL = "https://www.balldontlie.io/api/v1/stats?per_page=100&start_date=2022-10-21&end_date=2023-04-09&page=5";

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public Map<Integer, Player> getAllPlayers() {
        Map<Integer, Player> playerMap = new HashMap<>();


        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(apiURL,
                HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            JsonNode metaData = jsonNode.path("meta");


                for (int i = 0; i < root.size(); i++) {
                    Player player = null;
                    int id = root.path(i).path("player").path("id").asInt();
                    String firstName = root.path(i).path("player").path("first_name").asText();
                    String lastName = root.path(i).path("player").path("last_name").asText();
                    player = new Player(id, firstName, lastName);
                    playerMap.putIfAbsent(id, player);
                }

            } catch(JsonProcessingException e){
            e.printStackTrace();
            }
        System.out.println(playerMap.keySet());

        playerMap.forEach((k,v) -> playerDao.addPlayerToTable(v.getId(), v.getFirstName(), v.getLastName()));

        return playerMap;
    }

}
