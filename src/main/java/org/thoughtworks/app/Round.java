package org.thoughtworks.app;

public class Round {
    private static final int TOTAL_BOTTLE_NUM = 10;
    private int firstHit;
    private int secondHit;

    public Round(int firstHit) {
        this.firstHit = firstHit;
    }

    public static int totalBottleNum() {
        return TOTAL_BOTTLE_NUM;
    }

    public void secondHit(int secondHit) {
        this.secondHit = secondHit;
    }

    public int getScore() {
        return firstHit + secondHit;
    }

    public boolean isSpare() {
        return firstHit + secondHit == TOTAL_BOTTLE_NUM;
    }

    public int getFirstHit() {
        return firstHit;
    }

    int getBonusScore(Round nextRound) {
        if (isSpare()) {
            return nextRound.getFirstHit();
        }
        return 0;
    }

    public boolean isStrike() {
        return firstHit == TOTAL_BOTTLE_NUM;
    }
}
