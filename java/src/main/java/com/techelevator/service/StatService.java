package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.DualStatDto;
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
    private Map<LocalDate, Integer> statsMap = new LinkedHashMap<>();
    private DualStatDto dualStatDto;
    private Map<LocalDate, DualStatDto> dualStatMap = new LinkedHashMap<>();

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

    public Map<LocalDate, Integer> getPlayerSingleStat(int id, String category) {
        statsMap.clear();
        ResponseEntity<String> response = restTemplate.exchange(apiURL + id,
                HttpMethod.GET, httpEntity, String.class);

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            if (root.size() < 10) {
                for (int i = root.size() - 1; i >= 0; i--) {
                    if (category.equals("points")) {
                        mapNodePoints(root, i);
                    } else if (category.equals("assists")) {
                        mapNodeAssists(root, i);
                    } else if (category.equals("blocks")) {
                        mapNodeBlocks(root, i);
                    } else if (category.equals("rebounds")) {
                        mapNodeRebounds(root, i);
                    } else if (category.equals("steals")) {
                        mapNodeSteals(root, i);
                    }
                }
            } else {
                for (int i = root.size() - 1; i >= root.size() - 10; i--) {
                    if (category.equals("points")) {
                        mapNodePoints(root, i);
                    } else if (category.equals("assists")) {
                        mapNodeAssists(root, i);
                    } else if (category.equals("blocks")) {
                        mapNodeBlocks(root, i);
                    } else if (category.equals("rebounds")) {
                        mapNodeRebounds(root, i);
                    } else if (category.equals("steals")) {
                        mapNodeSteals(root, i);
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Iterator<Map.Entry<LocalDate, Integer>> itr = statsMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<LocalDate, Integer> entry = itr.next();
            System.out.println("Date: " + entry.getKey() + ", Number: " + entry.getValue());
        }
        return statsMap;
    }

    private void mapNodePoints(JsonNode node, int i) {
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int points = node.path(i).path("pts").asInt();
        statsMap.put(date, points);
    }
    private void mapNodeAssists(JsonNode node, int i) {
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int assists = node.path(i).path("ast").asInt();
        statsMap.put(date, assists);
    }
    private void mapNodeBlocks(JsonNode node, int i) {
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int blocks = node.path(i).path("blk").asInt();
        statsMap.put(date, blocks);
    }
    private void mapNodeRebounds(JsonNode node, int i) {
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int rebounds = node.path(i).path("reb").asInt();
        statsMap.put(date, rebounds);
    }
    private void mapNodeSteals(JsonNode node, int i) {
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int steals = node.path(i).path("stl").asInt();
        statsMap.put(date, steals);
    }

    public Map<LocalDate, DualStatDto> getPlayerDualStat(int id, String category) {
        dualStatMap.clear();
        ResponseEntity<String> response = restTemplate.exchange(apiURL + id,
                HttpMethod.GET, httpEntity, String.class);

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode root = jsonNode.path("data");
            if (root.size() < 10) {
                for (int i = root.size() - 1; i >= 0; i--) {
                    if (category.equals("field-goals")) {
                        mapNodeFieldGoals(root, i);
                    } else if (category.equals("threes")) {
                        mapNodeThrees(root, i);
                    } else if (category.equals("free-throws")) {
                        mapNodeFreeThrows(root, i);
                    }
                }
            } else {
                for (int i = root.size() - 1; i >= root.size() - 10; i--) {
                    if (category.equals("field-goals")) {
                        mapNodeFieldGoals(root, i);
                    } else if (category.equals("threes")) {
                        mapNodeThrees(root, i);
                    } else if (category.equals("free-throws")) {
                        mapNodeFreeThrows(root, i);
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return dualStatMap;
    }

    private void mapNodeFieldGoals(JsonNode node, int i) {
        dualStatDto = null;
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int fieldGoalsMade = node.path(i).path("fgm").asInt();
        int fieldGoalsAttempted = node.path(i).path("fga").asInt();
        dualStatDto = new DualStatDto(fieldGoalsMade, fieldGoalsAttempted);
        dualStatMap.put(date, dualStatDto);
    }

    private void mapNodeThrees(JsonNode node, int i) {
        dualStatDto = null;
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int threesMade = node.path(i).path("fg3m").asInt();
        int threesAttempted = node.path(i).path("fg3a").asInt();
        dualStatDto = new DualStatDto(threesMade, threesAttempted);
        dualStatMap.put(date, dualStatDto);
    }

    private void mapNodeFreeThrows(JsonNode node, int i) {
        dualStatDto = null;
        Instant instant = Instant.parse(node.path(i).path("game").path("date").asText());
        LocalDate date = instant.atZone(ZoneId.of("UTC")).toLocalDate();
        int freeThrowsMade = node.path(i).path("ftm").asInt();
        int freeThrowsAttempted = node.path(i).path("fta").asInt();
        dualStatDto = new DualStatDto(freeThrowsMade, freeThrowsAttempted);
        dualStatMap.put(date, dualStatDto);
    }
}
