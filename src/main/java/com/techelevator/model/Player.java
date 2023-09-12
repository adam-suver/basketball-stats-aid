package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    @JsonProperty("player_id")
    private int playerId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    public Player(int id, String firstName, String lastName) {
        this.playerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player() {
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
