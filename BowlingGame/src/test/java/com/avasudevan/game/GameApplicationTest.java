package com.avasudevan.game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class GameApplicationTest {

    GameApplication gameApplication;
    List<Player> playerList;
    public static final int NO_OF_FRAMES = 10;
    public static final int MAX_PINS = 10;

    @Before
    public void init() {
        Player player1 = new Player("Darth Wader");
        Player player2 = new Player("Obi Wan");
        Player player3 = new Player("Palpatine");
        playerList = Arrays.asList(player1,player2, player3);
        gameApplication = new GameApplication(Arrays.asList(1,2,3));
    }

    @Test
    public void testGamePlay() {
        int gameSessionId = gameApplication.createSession(playerList);
        for(int i = 0; i < NO_OF_FRAMES; i++) {
            for(Player p: playerList) {
                int rollValue = new Random().nextInt(MAX_PINS + 1);
                gameApplication.makeRoll(gameSessionId, p, rollValue, i);
                if(rollValue < MAX_PINS) {
                    rollValue = new Random().nextInt(MAX_PINS - rollValue);
                    gameApplication.makeRoll(gameSessionId, p, rollValue, i);
                }
            }
        }
        System.out.println("Winner is: "+gameApplication.declareWinner(gameSessionId));
    }
}
