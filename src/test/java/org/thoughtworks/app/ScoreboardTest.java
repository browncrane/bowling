package org.thoughtworks.app;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: crane
 * Date: 13-4-15
 * Time: 下午9:10
 */
public class ScoreboardTest {
    @Test
    public void should_return_score_for_certain_round(){
        Scoreboard scoreboard = new Scoreboard();
        int hitOne = 1;
        int hitTwo = 2;
        Round roundOne = new Round(hitOne,hitTwo);
        scoreboard.record(roundOne);
        assertThat(scoreboard.getHitOfRound(1),is(3));
    }
}
