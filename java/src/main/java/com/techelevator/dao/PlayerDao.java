package com.techelevator.dao;

import com.techelevator.model.Player;

import java.util.List;

public interface PlayerDao {

    List<Player> getAllPlayers();

    void addPlayerToTable(int playerId, String firstName, String lastName);

}
