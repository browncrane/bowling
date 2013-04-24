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
        roundHit1and2 = new Round();
        roundHit1and2.hit(1);
        roundHit1and2.hit(2);
        roundHit1and4 = new Round();
        roundHit1and4.hit(1);
        roundHit1and4.hit(4);
        roundHit4and4 = new Round();
        roundHit4and4.hit(4);
        roundHit4and4.hit(4);
        spareHit5and5 = new Round();
        spareHit5and5.hit(5);
        spareHit5and5.hit(5);
        strike = new Round();
        strike.hit(Round.TOTAL_BOTTLE_NUM);
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
    public void should_return_score_for_spare_round() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(spareHit5and5);
        scoreboard.record(roundHit4and4);
        assertThat(scoreboard.scoreByRound(2), is(17));
        assertThat(scoreboard.scoreByRound(3), is(25));
    }

    @Test
    public void should_return_score_for_strike_streak() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(strike);
        Round anotherStrike = new Round();
        anotherStrike.hit(Round.TOTAL_BOTTLE_NUM);
        scoreboard.record(anotherStrike);
        scoreboard.record(roundHit4and4);
        assertThat(scoreboard.scoreByRound(2), is(27));
    }

    @Test
    public void should_return_0_for_additional_hit_after_final_round_if_final_not_spare_or_round() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(spareHit5and5);
        scoreboard.record(roundHit4and4);
        assertThat(scoreboard.additionalHitAfterRound(3), is(0));
    }

    @Test
    public void should_return_1_for_additional_hit_after_final_round_if_final_is_spare() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(roundHit4and4);
        scoreboard.record(spareHit5and5);
        assertThat(scoreboard.additionalHitAfterRound(3), is(1));
    }

    @Test
    public void should_return_for_additional_hit_after_final_round_2_if_final_is_strike() throws Exception {
        scoreboard = new Scoreboard();
        scoreboard.record(roundHit1and2);
        scoreboard.record(roundHit4and4);
        scoreboard.record(strike);
        assertThat(scoreboard.additionalHitAfterRound(3), is(2));
    }
}