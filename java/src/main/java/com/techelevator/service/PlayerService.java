package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.PlayerDao;
import com.techelevator.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.*;

@Component
@CrossOrigin
public class PlayerService {

    @Autowired
    private final PlayerDao playerDao;

    private HttpEntity<String> httpEntity = new HttpEntity<>("");
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode jsonNode;

    @Value("${player.api.url}")
    private String apiURL;
    @Value("${stat.api.url}")
    private String statsApiURL;

    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public Map<Integer, Player> getAllPlayers() {
        Map<Integer, Player> playerMap = new HashMap<>();

        ResponseEntity<String> response = restTemplate.exchange(apiURL,
                HttpMethod.GET, httpEntity, String.class);

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

    public Map<LocalDate, Integer> getPlayerPoints(int id) {
        Map<LocalDate, Integer> pointsMap = new LinkedHashMap<>();

        ResponseEntity<String> response = restTemplate.exchange(statsApiURL + id,
                HttpMethod.GET, httpEntity, String.class);

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            JsonNode metaData = jsonNode.path("meta");
            for (int i = root.size() - 1; i >= root.size() - 10; i--) {
                Instant instant = Instant.parse(root.path(i).path("game").path("date").asText());
                LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
                int points = root.path(i).path("pts").asInt();
                pointsMap.put(date, points);
            }
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        Iterator<Map.Entry<LocalDate, Integer>> itr = pointsMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<LocalDate, Integer> entry = itr.next();
            System.out.println("Date: " + entry.getKey() +", Pts: " + entry.getValue());
        }
        return pointsMap;
    }
}
