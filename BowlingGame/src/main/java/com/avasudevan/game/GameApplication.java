package com.avasudevan.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameApplication {

    List<Integer> alleys;
    Map<Integer, GameSession> gameSessionMap;

    GameApplication(List<Integer> alleys) {
        this.alleys = alleys;
        this.gameSessionMap = new HashMap<>();
    }

    public void makeRoll(int gameSessionId, Player player, int rollValue, int frameNo) {
        GameSession gameSession = gameSessionMap.get(gameSessionId);
        gameSession.roll(player, rollValue, frameNo);
    }

    public int createSession(List<Player> players) {
        GameSession gameSession = new GameSession();
        // Get the last alley from the Alley List
        int alley = alleys.get(alleys.size() - 1);
        int gameSessionId = gameSession.createSession(alley, players);
        gameSessionMap.put(gameSessionId, gameSession);
        return gameSessionId;
    }

    public String declareWinner(int gameSessionId) {
        return gameSessionMap.get(gameSessionId).declareWinner();
    }
}
