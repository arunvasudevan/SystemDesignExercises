package com.avasudevan.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private int score;
    private final List<Integer> rolls;
    boolean firstRoll;
    boolean canPlay;
    public static final int NO_OF_FRAMES = 10;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.rolls = new ArrayList<>();
        this.firstRoll = true;
        this.canPlay = true;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean isSpare() {
        return rolls.size() >= 2 && (rolls.get(rolls.size() -1) + rolls.get(rolls.size() - 2) == 10);
    }

    public void roll(int rollValue, int frameNo) {
        if(!canPlay) {
            return;
        }

        score += rollValue;
        rolls.add(rollValue);

        if(frameNo >= NO_OF_FRAMES - 1 && !firstRoll && (!isSpare() || !isStrike())) {
            canPlay = false;
        }

        if (firstRoll && isStrike()) {
            score += 10;
        } else if (!firstRoll && isSpare()) {
            score += 5;
        } else {
            firstRoll = !firstRoll;
        }
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public boolean isStrike() {
        return rolls.size() > 0 && (rolls.get(rolls.size() - 1) == 10);
    }
}
