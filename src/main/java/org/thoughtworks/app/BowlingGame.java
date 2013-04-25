package org.thoughtworks.app;

public class BowlingGame {
    private static final int GAME_LENGTH = 10;
    private Scoreboard scoreboard;
    private Round currentRound;

    public void hit(int hitScore) {
        if (normalRoundFinished() && additionalRoundFinished()) throw new RuntimeException();
        if (!normalRoundFinished()) {
            currentRound.hit(hitScore);
            if (currentRound.isFinish() ) {
                scoreboard.record(currentRound);
                currentRound = new Round();
            }
        }
        if(normalRoundFinished() && additionalHit() == 1){
            currentRound.hit(hitScore);
            scoreboard.record(currentRound);
            currentRound = new Round();
        }
    }

    public BowlingGame() {
        this.scoreboard = new Scoreboard();
        this.currentRound = new Round();
    }

    //when start with no hit,it's already round 1
    public int currentRoundNumber() {
        return scoreboard.finishedRound() + 1;
    }

    public boolean gameOver() {
        return normalRoundFinished() && additionalRoundFinished();
    }

    private boolean additionalRoundFinished() {
        return additionalHit() == 0;
    }

    public int additionalHit() {
        return scoreboard.additionalHitAfterRound(GAME_LENGTH);
    }

    private boolean normalRoundFinished() {
        return currentRoundNumber() > GAME_LENGTH;
    }

    public int getScoreByRound(int roundNumber) {
        return scoreboard.scoreByRound(roundNumber);
    }
}