package org.thoughtworks.app;

public class BowlingGame {
    private static final int GAME_LENGTH = 10;
    public static final int BONUS_HITS_FOR_FINAL_IS_STRIKE = 2;
    public static final int BONUS_HITS_FOR_FINAL_IS_SPARE = 1;
    private Scoreboard scoreboard;
    private Round currentRound;
    private int additionalHitUsed = 0;

    public void hit(int hitScore) {
        checkIfGameOver();
        if (normalRoundFinished()) {
            makeRoundForAdditionalGame(hitScore);
        } else {
            makeRoundForNormalGame(hitScore);
        }
    }

    private void makeRoundForNormalGame(int hitScore) {
        currentRound.hit(hitScore);
        recordCompleteRound();
    }

    private void makeRoundForAdditionalGame(int hitScore) {
        currentRound.hit(hitScore);
        recordCompleteRound();
        recordIncompleteRoundInAdditionalGame();
    }

    private void checkIfGameOver() {
        if (normalRoundFinished()) {
            additionalHitUsed++;
            if (noAdditionalRound() || additionalHitUsed > additionalHits())
                throw new RuntimeException();
        }
    }

    private void recordCompleteRound() {
        if (currentRound.isFinish()) {
            recordCurrentRoundStartNext();
        }
    }

    private void recordCurrentRoundStartNext() {
        scoreboard.record(currentRound);
        currentRound = new Round();
    }

    private void recordIncompleteRoundInAdditionalGame() {
        if (currentRoundIsAdditionalRoundForSpare()) {
            recordCurrentRoundStartNext();
        }
        if (finalRoundIsStrikeStreak()) {
            recordCurrentRoundStartNext();
        }
    }

    private boolean finalRoundIsStrikeStreak() {
        return finalRoundIsStrike() && currentRoundNumber() == GAME_LENGTH + BONUS_HITS_FOR_FINAL_IS_STRIKE && additionalHitUsed == BONUS_HITS_FOR_FINAL_IS_STRIKE;
    }

    private boolean finalRoundIsStrike() {
        return additionalHits() == BONUS_HITS_FOR_FINAL_IS_STRIKE;
    }

    private boolean currentRoundIsAdditionalRoundForSpare() {
        return additionalHitUsed == BONUS_HITS_FOR_FINAL_IS_SPARE && finalRoundIsSpare();
    }

    private boolean finalRoundIsSpare() {
        return additionalHits() == BONUS_HITS_FOR_FINAL_IS_SPARE;
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
        return additionalHits() == 0;
    }

    public int additionalHits() {
        return scoreboard.additionalHitAfterRound(GAME_LENGTH);
    }

    private boolean normalRoundFinished() {
        return currentRoundNumber() > GAME_LENGTH;
    }

    public int getScoreByRound(int roundNumber) {
        return scoreboard.scoreByRound(roundNumber);
    }
}