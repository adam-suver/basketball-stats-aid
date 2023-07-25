package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Player;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerService {

    private String apiURL = "https://www.balldontlie.io/api/v1/stats?per_page=100&start_date=2022-10-21&end_date=2023-04-09&page=";

    public List<Player> getAllPlayers() {
        List<Player> playerList = new ArrayList<>();
        Player player = null;

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        int currentPageNumber = 1;
        int nextPageNumber = 2;
        while (currentPageNumber <= 5) {
        ResponseEntity<String> response = restTemplate.exchange(apiURL + currentPageNumber,
                HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            JsonNode metaData = jsonNode.path("meta");
//            currentPageNumber = metaData.path("current_page").asInt();
            nextPageNumber = metaData.path("next_page").asInt();

                for (int i = 0; i < root.size(); i++) {
                    int id = root.path(i).path("player").path("id").asInt();
                    String firstName = root.path(i).path("player").path("first_name").asText();
                    String lastName = root.path(i).path("player").path("last_name").asText();
                    player = new Player(id, firstName, lastName);
                    if (!playerList.contains(player)) {
                        playerList.add(player);
                    }
                }
                currentPageNumber++;
            } catch(JsonProcessingException e){
            e.printStackTrace();
            }
        }
        return playerList;
    }

}
