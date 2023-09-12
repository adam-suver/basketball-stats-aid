package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Game {

    @JsonProperty("id")
    private long gameId;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("ast")
    private int assists;
    @JsonProperty("blk")
    private int blocks;
    @JsonProperty("fg3a")
    private int threePointAttempts;
    @JsonProperty("fg3m")
    private int threePointMade;
    @JsonProperty("fga")
    private int fieldGoalAttempts;
    @JsonProperty("fgm")
    private int fieldGoalsMade;
    @JsonProperty("fta")
    private int freeThrowAttempts;
    @JsonProperty("ftm")
    private int freeThrowsMade;
    @JsonProperty("min")
    private int minutes;
    @JsonProperty("pts")
    private int points;
    @JsonProperty("reb")
    private int rebounds;
    @JsonProperty("stl")
    private int steals;



    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getThreePointAttempts() {
        return threePointAttempts;
    }

    public void setThreePointAttempts(int threePointAttempts) {
        this.threePointAttempts = threePointAttempts;
    }

    public int getThreePointMade() {
        return threePointMade;
    }

    public void setThreePointMade(int threePointMade) {
        this.threePointMade = threePointMade;
    }

    public int getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public void setFieldGoalAttempts(int fieldGoalAttempts) {
        this.fieldGoalAttempts = fieldGoalAttempts;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(int fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public int getFreeThrowAttempts() {
        return freeThrowAttempts;
    }

    public void setFreeThrowAttempts(int freeThrowAttempts) {
        this.freeThrowAttempts = freeThrowAttempts;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(int freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }
}
