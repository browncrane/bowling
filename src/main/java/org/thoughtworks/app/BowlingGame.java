package org.thoughtworks.app;

public class BowlingGame {
    private static final int GAME_LENGTH = 10;
    private Scoreboard scoreboard;
    private Round currentRound;

    public void hit(int hitScore) {
        if (normalRoundFinished() && noAdditionalRound()) throw new RuntimeException();
        currentRound.hit(hitScore);
        if(finalRoundIsSpare()){
            recordCurrentRoundStartNext();
        }
        if (currentRound.isFinish()) {
            recordCurrentRoundStartNext();
        }
    }

    private boolean finalRoundIsSpare() {
        return !currentRound.isFinish() && normalRoundFinished() && additionalHit() == 1;
    }

    private void recordCurrentRoundStartNext() {
        scoreboard.record(currentRound);
        currentRound = new Round();
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
        return normalRoundFinished() && noAdditionalRound();
    }

    private boolean noAdditionalRound() {
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