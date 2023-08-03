package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatService {

    private HttpEntity<String> httpEntity = new HttpEntity<>("");
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode jsonNode;
    private Map<LocalDate, Integer> pointsMap = new LinkedHashMap<>();

    @Value("${stat.api.url}")
    private String apiURL;

    public List<Game> getSearchResults(int playerId, String category) {
        String url = this.apiURL + playerId;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            JsonNode page = jsonNode.path("meta");
            System.out.println(page);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<LocalDate, Integer> getPlayerPoints(int id) {
        ResponseEntity<String> response = restTemplate.exchange(apiURL + id,
                HttpMethod.GET, httpEntity, String.class);

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            JsonNode metaData = jsonNode.path("meta");
            if (root.size() < 10) {
                for (int i = root.size() - 1; i >= 0; i--) {
                    mapNodePoints(root, i);
                }
            } else {
                for (int i = root.size() - 1; i >= root.size() - 10; i--) {
                    mapNodePoints(root, i);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Iterator<Map.Entry<LocalDate, Integer>> itr = pointsMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<LocalDate, Integer> entry = itr.next();
            System.out.println("Date: " + entry.getKey() + ", Pts: " + entry.getValue());
        }
        return pointsMap;
    }
    public void mapNodePoints(JsonNode node, int i) {
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int points = node.path(i).path("pts").asInt();
        pointsMap.put(date, points);
    }
}
