package org.thoughtworks.app;

public class BowlingGame {
    private static final int GAME_LENGTH = 11;
    private Scoreboard scoreboard;
    private Round currentRound;

    public void hit(int hitScore) {
        if (gameOver()) throw new RuntimeException();
        currentRound.hit(hitScore);
        if (currentRound.isFinish()) {
            scoreboard.record(currentRound);
            currentRound = new Round();
        }
    }

    public BowlingGame() {
        this.scoreboard = new Scoreboard();
        this.currentRound = new Round();
    }

    public int currentRoundNumber() {
        return scoreboard.currentRoundNumber();
    }

    public boolean gameOver() {
        return currentRoundNumber() == GAME_LENGTH + additionalHit();
    }

    public int additionalHit() {
        return scoreboard.additionalHit(GAME_LENGTH);
    }

    public int getScoreByRound(int roundNumber) {
        return scoreboard.scoreByRound(roundNumber);
    }
}