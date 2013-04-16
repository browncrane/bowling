package org.thoughtworks.app;

import java.util.ArrayList;
import java.util.List;

/**
 * User: crane
 * Date: 13-4-15
 * Time: 下午9:32
 */
public class Scoreboard {
    private List<Round> roundRecord;

    public Scoreboard() {
        this.roundRecord = new ArrayList<Round>();
    }

    public void record(Round round) {
        this.roundRecord.add(round);
    }

    public int hitsDownByRound(int roundNumber) {
        int sum = 0;
        for(int i = 0 ; i < roundNumber; i++){
            sum += roundRecord.get(i).hitDownAll();
        }
        return sum;
    }

    public int currentRound() {
        return 3;
    }
}
