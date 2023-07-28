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

    private String apiURL = "${player.api.url}";
    private String statsApiURL = "https://www.balldontlie.io/api/v1/stats?seasons[]=2022&per_page=100&player_ids[]=";

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

    public Map<String, Integer> getPlayerPoints(int id) {
        Map<String, Integer> pointsMap = new HashMap<>();

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(statsApiURL + id,
                HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            JsonNode metaData = jsonNode.path("meta");
            for (int i = root.size() - 1; i >= root.size() - 10; i--) {
                String date = root.path(i).path("game").path("date").asText();
                int points = root.path(i).path("pts").asInt();
                pointsMap.put(date, points);
            }

        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        Iterator<Map.Entry<String, Integer>> itr = pointsMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            System.out.println("Date:" + entry.getKey() +", Pts: " + entry.getValue());
        }
        return pointsMap;
    }

}
