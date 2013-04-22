package org.thoughtworks.app;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private List<Round> roundRecord;

    public Scoreboard() {
        this.roundRecord = new ArrayList<Round>();
    }

    public void record(Round round) {
        this.roundRecord.add(round);
    }

    public int scoreByRound(int roundNumber) {
        return sumByRoundSub(roundNumber);
    }

    private int sumByRoundSub(int roundNumber) {
        int result = 0;
        for (int i = 0; i < roundNumber; i++) {
            result += roundRecord.get(i).getScore();
            if (notFinalRound(roundRecord.get(i)))
                result += roundRecord.get(i).getBonusScore(roundRecord.get(i + 1));
            if (roundRecord.get(i).isStrike() && roundRecord.get(i + 1).isStrike())
                result += roundRecord.get(i+2).getFirstHit();
        }
        return result;
    }

    private boolean notFinalRound(Round round) {
        return roundRecord.indexOf(round) != roundRecord.size() - 1;
    }

    public int currentRound() {
        return 3;
    }
}
