package com.avasudevan.snakesandladders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class BoardGameTest {

    List<Player> players = new ArrayList<>();
    Map<Integer, Integer> snakeAndLadderMap;
    BoardGame boardGame;

    @Before
    public void init() {
        // Set the ladder and snake map with corresponding snake and ladder head and tails
        // Initializes all players and generates a random roll value (1 to 6) and calls move in the game board
        // Keep calling until a winner is declared.

        Player player1 = new Player("Tintin", "Blue");
        Player player2 = new Player("Snowy", "White");

        players = Arrays.asList(player1, player2);

        snakeAndLadderMap = ImmutableMap.of(5, 15,
            18, 48,
            52, 30,
            82, 16,
            90, 72
        );

        boardGame = new BoardGame(snakeAndLadderMap, players);
    }

    @Test
    public void testBoardGame() {
        boolean continueGame = true;

        while(continueGame) {
            for (Player p : players) {
                int rollValue = new Random().nextInt(6) + 1;
                // System.out.println("Player: " + p.getName() + ", Roll Value:" + rollValue);
                if (boardGame.move(p, rollValue)) {
                    System.out.println("Winner is: " + boardGame.declareWinner());
                    continueGame = false;
                    break;
                }
            }
        }
    }
}
