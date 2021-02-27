package com.avasudevan.game;

import java.util.List;

public class GameSession {

    List<Player> players;
    int alley;
    int id;
    private int lastId;

    public int createSession(int alley, List<Player> players) {
        this.players = players;
        this.id = lastId++;
        this.alley = alley;

        return this.id;
    }

    public void roll(Player player, int rollValue, int frameNo) {
        this.players.stream()
            .filter(p -> p.getName().equalsIgnoreCase(player.getName()))
            .findFirst().ifPresent(value -> value.roll(rollValue, frameNo));
    }

    public String declareWinner() {
        int maxScore = 0;
        String winner = "";
        for (Player player : players) {
            System.out.print(player.getName() + ": ");
            player.getRolls()
                .forEach(t -> System.out.print(t + " "));
            System.out.println();
            System.out.println("Score:" + player.getScore());
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                winner = player.getName();
            }
        }
        return winner;
    }

}
