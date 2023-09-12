package com.techelevator.model;

import java.time.LocalDate;

public class DualStatDto {
    private int shotsMade;
    private int attempts;

    public DualStatDto(int shotsMade, int attempts) {
        this.shotsMade = shotsMade;
        this.attempts = attempts;
    }

    public DualStatDto() {
    }

    public int getShotsMade() {
        return shotsMade;
    }

    public void setShotsMade(int shotsMade) {
        this.shotsMade = shotsMade;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
