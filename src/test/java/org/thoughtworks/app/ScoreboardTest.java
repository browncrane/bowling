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
        int hitFour = 4;
        Round roundOne = new Round(hitOne,hitTwo);
        Round roundTwo = new Round(hitOne,hitFour);
        Round roundThree = new Round(hitFour,hitFour);
        scoreboard.record(roundOne);
        scoreboard.record(roundTwo);
        scoreboard.record(roundThree);
        assertThat(scoreboard.hitsDownByRound(2),is(8));
        assertThat(scoreboard.hitsDownByRound(3),is(16));
    }
}
