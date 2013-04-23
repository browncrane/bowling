package org.thoughtworks.app;

public class Round {
    public static final int TOTAL_BOTTLE_NUM = 10;
    private int firstHit;
    private int secondHit;

    public Round(int firstHit) {
        this.firstHit = firstHit;
    }

    public void secondHit(int secondHit) {
        this.secondHit = secondHit;
    }

    public int getScore() {
        return firstHit + secondHit;
    }

    public boolean isSpare() {
        return firstHit + secondHit == TOTAL_BOTTLE_NUM && !isStrike();
    }

    public int getFirstHit() {
        return firstHit;
    }

    int getBonusScore(Round nextRound) {
        if (isStrike()) {
            return nextRound.getScore();
        }
        if (isSpare()) {
            return nextRound.getFirstHit();
        }
        return 0;
    }

    public boolean isStrike() {
        return firstHit == TOTAL_BOTTLE_NUM;
    }

    public boolean isStrikeStreak(Round nextRound) {
        return isStrike() && nextRound.isStrike();
    }
}
