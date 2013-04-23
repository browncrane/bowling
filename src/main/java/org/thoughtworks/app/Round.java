package org.thoughtworks.app;

public class Round {
    public static final int TOTAL_BOTTLE_NUM = 10;
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
        if (isStrike() || hitCount == 2) {
            return true;
        } else {
            return false;
        }
    }

    void hit(int hitScore) {
        if (notStarted()) {
            setFirstHit(hitScore);
        } else {
            setSecondHit(hitScore);
        }
    }
}
