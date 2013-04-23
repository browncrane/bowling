package org.thoughtworks.app;

public class BowlingGame {
    private static final int ROUND_AFTER_NORMAL_LENGTH = 11;
    private Scoreboard scoreboard;
    private boolean cleanRound;
    private Round currentRound;

    public void hit(int hitScore) {
        checkHitScore(hitScore);
        if (gameEnd())
            throw new RuntimeException();
        makeRoundFromHit(hitScore);
        makeRoundIfStrike();
    }

    private void checkHitScore(int hitScore) {
        if (hitScore > Round.TOTAL_BOTTLE_NUM || hitScore < 0)
            throw new RuntimeException();
        if ((!cleanRound) && (hitScore + currentRound.getFirstHit() > Round.TOTAL_BOTTLE_NUM))
            throw new RuntimeException();
    }

    private void makeRoundFromHit(int hitScore) {
        if (cleanRound) {
            currentRound = new Round(hitScore);
            cleanRound = false;
        } else {
            currentRound.secondHit(hitScore);
            makeRound();
        }
    }

    private void makeRoundIfStrike() {
        if (currentRound.getFirstHit() == Round.TOTAL_BOTTLE_NUM) {
            makeRound();
        }
    }

    private void makeRound() {
        cleanRound = true;
        scoreboard.record(currentRound);
    }

    public BowlingGame() {
        this.scoreboard = new Scoreboard();
        cleanRound = true;
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
