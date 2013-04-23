package org.thoughtworks.app;

public class BowlingGame {
    private Scoreboard scoreboard;
    private boolean cleanRound;
    private Round currentRound;

    public void hit(int hitScore) {
        checkForHitScore(hitScore);
        makeRoundFromHit(hitScore);
        makeRoundIfStrike();
    }

    private void checkForHitScore(int hitScore) {
        if (hitScore > 10 || hitScore < 0)
            throw new RuntimeException();
        if(!cleanRound && (currentRound.getFirstHit() + hitScore > 10))
            throw new RuntimeException();
    }

    private void makeRoundFromHit(int hitScore) {
        if (cleanRound) {
            currentRound = new Round(hitScore);
            cleanRound = false;
        }else {
            currentRound.secondHit(hitScore);
            makeRound();
        }
    }

    private void makeRoundIfStrike() {
        if (currentRound.getFirstHit() == Round.totalBottleNum()) {
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
}
