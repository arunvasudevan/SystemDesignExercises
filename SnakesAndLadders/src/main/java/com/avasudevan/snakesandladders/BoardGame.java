package com.avasudevan.snakesandladders;

import java.util.List;
import java.util.Map;

public class BoardGame {

    Map<Integer, Integer> snakeAndLadderMap;
    List<Player> playerList;
    int currentTurn;

    public static final int MAX_BOARD_POSITION = 100;

    public BoardGame(Map<Integer, Integer> snakeAndLadderMap, List<Player> playerList) {
        this.snakeAndLadderMap = snakeAndLadderMap;
        this.playerList = playerList;
        this.currentTurn = 0;
    }

    boolean move(Player player, int rollValue) {
        // Adds the roll value to the player position, If position is greater than 100 then return false
        // Else, gets the new value and updates it.
        int newPosition = player.getPosition() + rollValue;

        if(currentTurn != 0 && currentTurn == player.getId()) {
            return false;
        }
        currentTurn = (currentTurn + 1) % playerList.size();

        if(newPosition <= MAX_BOARD_POSITION) {
            player.setPosition(snakeAndLadderMap.getOrDefault(newPosition, newPosition));
        }

        return player.getPosition() == MAX_BOARD_POSITION;
    }

    String declareWinner() {
        // Prints all players position and returns the player with pos equals 100.
        Player winner = null;
        for(Player player: playerList) {
            System.out.println(player.getName() + ", Id:"+ player.getId() + " ("+ player.getPin() + ")"+": " + player.getPosition());
            if(player.getPosition() == MAX_BOARD_POSITION) {
                winner = player;
            }
        }

        return winner != null ? winner.getName(): "";
    }
}
