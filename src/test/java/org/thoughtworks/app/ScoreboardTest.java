package org.thoughtworks.app;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: crane
 * Date: 13-4-15
 * Time: 下午9:10
 */
public class ScoreboardTest {

    private Round roundOne;
    private Round roundTwo;
    private Round roundThree;
    private Scoreboard scoreboard;
    private Round spareRound;

    @Before
    public void SetUp() {
        int hitOne = 1;
        int hitTwo = 2;
        int hitFour = 4;
        int hitFive = 5;
        roundOne = new Round(hitOne, hitTwo);
        roundTwo = new Round(hitOne, hitFour);
        roundThree = new Round(hitFour, hitFour);
        spareRound = new Round(hitFive, hitFive);
    }

    @Test
    public void should_return_score_for_certain_round() {
        scoreboard = new Scoreboard();
        scoreboard.record(roundOne);
        scoreboard.record(roundTwo);
        scoreboard.record(roundThree);
        assertThat(scoreboard.hitsDownByRound(2), is(8));
        assertThat(scoreboard.hitsDownByRound(3), is(16));
    }

    @Test
    public void should_return_current_round_if_no_strike() {
        scoreboard = new Scoreboard();
        scoreboard.record(roundOne);
        scoreboard.record(roundTwo);
        scoreboard.record(roundThree);
        assertThat(scoreboard.currentRound(), is(3));
    }

    @Test
    public void should_return_score_for_spare_round() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundOne);
        scoreboard.record(spareRound);
        scoreboard.record(roundThree);
//        assertThat(scoreboard.hitsDownByRound(2),is(14));
    }
}
