package org.thoughtworks.app;

public class BowlingGame {
    private static final int ROUND_AFTER_NORMAL_LENGTH = 11;
    private Scoreboard scoreboard;
    private Round currentRound;

    public void hit(int hitScore) {
        if (gameEnd()) throw new RuntimeException();
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

    public int currentRound() {
        return scoreboard.currentRound();
    }

    public boolean gameEnd() {
        return scoreboard.currentRound() == ROUND_AFTER_NORMAL_LENGTH + additionalHit();
    }

    public int additionalHit() {
        return scoreboard.additionalHit(ROUND_AFTER_NORMAL_LENGTH);
    }

    public int getScoreByRound(int roundNumber) {
        return scoreboard.scoreByRound(roundNumber);
    }
}