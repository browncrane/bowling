package org.thoughtworks.app;

public class BowlingGame {
    private static final int GAME_LENGTH = 10;
    public static final int BONUS_HIT_FOR_FINAL_IS_STRIKE = 2;
    public static final int BONUS_HIT_FOR_FINAL_IS_SPARE = 1;
    private Scoreboard scoreboard;
    private Round currentRound;

    public void hit(int hitScore) {
        if (normalRoundFinished() && noAdditionalRound()) throw new RuntimeException();
        currentRound.hit(hitScore);
        makeRoundForIncompleteRoundInAdditionalRound();
        if (currentRound.isFinish()) {
            recordCurrentRoundStartNext();
        }
    }

    private void makeRoundForIncompleteRoundInAdditionalRound() {
        if(currentRoundIsAdditionalRoundForSpare()){
            recordCurrentRoundStartNext();
        }
        if(finalRoundIsStrikeStreak()){
            recordCurrentRoundStartNext();
        }
    }

    private boolean finalRoundIsStrikeStreak() {
        return normalRoundFinished() && finalRoundIsStrike() && !currentRound.isFinish() && currentRoundNumber() > GAME_LENGTH + 1;
    }

    private boolean finalRoundIsStrike() {
        return additionalHit() == BONUS_HIT_FOR_FINAL_IS_STRIKE;
    }

    private boolean currentRoundIsAdditionalRoundForSpare() {
        return !currentRound.isFinish() && normalRoundFinished() && finalRoundIsSpare();
    }

    private boolean finalRoundIsSpare() {
        return additionalHit() == BONUS_HIT_FOR_FINAL_IS_SPARE;
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