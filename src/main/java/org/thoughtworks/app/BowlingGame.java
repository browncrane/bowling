package org.thoughtworks.app;

public class BowlingGame {
    private Scoreboard scoreboard;
    private boolean cleanRound;
    private Round currentRound;

    public void hit(int hitScore) {
        makeRoundFromHit(hitScore);
        makeRoundIfStrike();
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
