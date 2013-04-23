package org.thoughtworks.app;

public class BowlingGame {
    private int firstHitInRound;
    private int secondHitInRound;

    public void firstHitInRound(int hitScore) {
        firstHitInRound = hitScore;
    }


    public void secondHitInRound(int hitScore) {
        secondHitInRound = hitScore;
    }

    public Round currentRound() {
        Round round = new Round(firstHitInRound);
        round.secondHit(this.secondHitInRound);
        return round;
    }


}
