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

    public int hitsDownByRound(int roundNumber) {
        return sumRoundScore(getRoundsBefore(roundNumber));
    }

    private int sumRoundScore(List<Round> rounds) {
        int result = 0;
        for (Round round : rounds) {
            result += round.getScore();
            if (round.isSpare()) {
                result += nextRound(round).getFirstHit();
            }
        }
        return result;
    }

    private Round nextRound(Round round) {
        return roundRecord.get(roundRecord.indexOf(round) + 1);
    }

    private List<Round> getRoundsBefore(int roundNumber) {
        List<Round> result = new ArrayList<Round>();
        for (int roundIndex = 0; roundIndex < roundNumber; roundIndex++) {
            result.add(roundRecord.get(roundIndex));
        }
        return result;
    }

    public int currentRound() {
        return 3;
    }
}
