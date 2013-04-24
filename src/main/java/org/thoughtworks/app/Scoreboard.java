package org.thoughtworks.app;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    public static final int BONUS_HITS_FOR_SPARE = 1;
    public static final int BONUS_HITS_FOR_STRIKE = 2;
    private List<Round> roundRecord;

    public Scoreboard() {
        this.roundRecord = new ArrayList<Round>();
    }

    public void record(Round round) {
        this.roundRecord.add(round);
    }

    public int scoreByRound(int roundNumber) {
        return sumRoundScore(getRoundsBefore(roundNumber));
    }

    private int sumRoundScore(List<Round> rounds) {
        int sumScore = 0;
        for (Round round : rounds) {
            sumScore += round.getHitsScoreInRound();
            if (notFinalRound(round)) {
                sumScore += round.getBonusScore(nextRoundOf(round)) + getBonusForStrikeStreak(round);
            }
        }
        return sumScore;
    }

    private int getBonusForStrikeStreak(Round round) {
        if (round.isStrikeStreak(nextRoundOf(round)))
            return nextRoundOf(nextRoundOf(round)).getFirstHit();
        return 0;
    }

    private boolean notFinalRound(Round round) {
        return roundRecord.indexOf(round) != roundRecord.size() - 1;
    }

    //find round in this way,make us can input one round twice
    //But they should be two round with same value
    public Round nextRoundOf(Round round) {
        return roundRecord.get(roundRecord.indexOf(round) + 1);
    }

    private List<Round> getRoundsBefore(int roundNumber) {
        List<Round> result = new ArrayList<Round>();
        for (int roundIndex = 0; roundIndex < roundNumber; roundIndex++) {
            result.add(roundRecord.get(roundIndex));
        }
        return result;
    }

    //when start with no hit,it's already round 1
    int currentRoundNumber() {
        return roundRecord.size() + 1;
    }

    public int additionalHit(int finalRoundNumber) {
        if (roundRecord.size() == finalRoundNumber) {
            if (getFinalRound().isSpare())
                return BONUS_HITS_FOR_SPARE;
            if (getFinalRound().isStrike())
                return BONUS_HITS_FOR_STRIKE;
            return 0;
        }
        return 0;
    }

    private Round getFinalRound() {
        return roundRecord.get(9);
    }

}
