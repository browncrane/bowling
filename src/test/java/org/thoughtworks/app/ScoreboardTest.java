package org.thoughtworks.app;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ScoreboardTest {

    private Round roundHit1and2;
    private Round roundHit1and4;
    private Round roundHit4and4;
    private Scoreboard scoreboard;
    private Round spareHit5and5;
    private Round strike;

    @Before
    public void SetUp() {
        int hitOne = 1;
        int hitTwo = 2;
        int hitFour = 4;
        int hitFive = 5;
        roundHit1and2 = new Round(hitOne);
        roundHit1and2.secondHit(hitTwo);
        roundHit1and4 = new Round(hitOne);
        roundHit1and4.secondHit(hitFour);
        roundHit4and4 = new Round(hitFour);
        roundHit4and4.secondHit(hitFour);
        spareHit5and5 = new Round(hitFive);
        spareHit5and5.secondHit(hitFive);
        strike = new Round(Round.totalBottleNum());
    }

    @Test
    public void should_return_score_for_certain_round() {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(roundHit1and4);
        scoreboard.record(roundHit4and4);
        assertThat(scoreboard.scoreByRound(2), is(8));
        assertThat(scoreboard.scoreByRound(3), is(16));
    }

    @Test
    public void should_return_current_round_if_no_strike() {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(roundHit1and4);
        scoreboard.record(roundHit4and4);
        assertThat(scoreboard.currentRound(), is(3));
    }

    @Test
    public void should_return_score_for_spare_round() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(spareHit5and5);
        scoreboard.record(roundHit4and4);
        assertThat(scoreboard.scoreByRound(2), is(17));
        assertThat(scoreboard.scoreByRound(3), is(25));
    }

    @Test
    public void should_return_score_for_strike_round() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(strike);
        Round anotherStrike = new Round(Round.totalBottleNum());
        scoreboard.record(anotherStrike);
        scoreboard.record(roundHit4and4);
        assertThat(scoreboard.scoreByRound(2), is(27));
    }
}
