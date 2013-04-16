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


    public int getHitOfRound(int roundNumber) {
        return 3;
    }
}
