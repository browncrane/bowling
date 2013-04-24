package org.thoughtworks.app;

public class Round {
    public static final int TOTAL_BOTTLE_NUM = 10;
    public static final int MAX_TRY_IN_ROUND = 2;
    private int firstHit;
    private int secondHit;
    private int hitCount;

    public Round() {
        this.hitCount = 0;
    }

    private void setSecondHit(int secondHit) {
        checkSecondHit(secondHit);
        this.hitCount++;
        this.secondHit = secondHit;
    }

    public int getHitsScoreInRound() {
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
            return nextRound.getHitsScoreInRound();
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

    public boolean notStarted() {
        return hitCount == 0;
    }

    private void setFirstHit(int firstHit) {
        checkFirstHit(firstHit);
        this.firstHit = firstHit;
        this.hitCount++;
    }

    private void checkFirstHit(int firstHit) {
        if (firstHit > TOTAL_BOTTLE_NUM || firstHit < 0)
            throw new RuntimeException();
    }

    private void checkSecondHit(int hitScore) {
        if (hitScore + getFirstHit() > TOTAL_BOTTLE_NUM)
            throw new RuntimeException();
    }

    public boolean isFinish() {
        return isStrike() || hitCount == MAX_TRY_IN_ROUND;
    }

    void hit(int hitScore) {
        checkForHitCounts();
        if (notStarted()) {
            setFirstHit(hitScore);
        } else {
            setSecondHit(hitScore);
        }
    }

    private void checkForHitCounts() {
        if (isFinish())
            throw new RuntimeException();
    }
}
