package org.thoughtworks.app;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoundTest {
    private Round normalHit1and4;
    private Round spareHit5and5;
    private Round strikeRound;

    @Before
    public void SetUp() {
        normalHit1and4 = new Round();
        normalHit1and4.hit(1);
        normalHit1and4.hit(4);
        spareHit5and5 = new Round();
        spareHit5and5.hit(5);
        spareHit5and5.hit(5);
        strikeRound = new Round();
        strikeRound.hit(Round.TOTAL_BOTTLE_NUM);
    }

    @Test
    public void should_return_the_sum_of_two_hits() {
        assertThat(normalHit1and4.getHitsScoreInRound(), is(5));
        assertThat(spareHit5and5.getHitsScoreInRound(), is(10));
    }

    @Test
    public void should_return_true_if_the_round_is_spare() throws Exception {
        assertThat(spareHit5and5.isSpare(), is(true));
        assertThat(normalHit1and4.isSpare(), is(false));
    }

    @Test
    public void should_return_first_hit_score() throws Exception {
        assertThat(normalHit1and4.getFirstHit(), is(1));
        assertThat(spareHit5and5.getFirstHit(), is(5));
    }

    @Test
    public void should_return_true_if_the_round_is_strike() throws Exception {
        assertThat(strikeRound.isStrike(), is(true));
        assertThat(spareHit5and5.isStrike(), is(false));
    }

    @Test
    public void should_return_true_for_is_strike_streak_if_hit_all_down_continuous() throws Exception {
        assertStrikeStreak(strikeRound, strikeRound, true);
    }

    @Test
    public void should_return_false_for_is_strike_streak_if_not_hit_all_down() throws Exception {
        assertStrikeStreak(normalHit1and4, strikeRound, false);
    }

    private void assertStrikeStreak(Round currentRound, Round nextRound, boolean result) {
        assertThat(currentRound.isStrikeStreak(nextRound), is(result));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_if_two_hits_more_than_10() throws RuntimeException {
        Round round = new Round();
        round.hit(7);
        round.hit(7);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_if_hit_score_bigger_than_10() throws RuntimeException {
        Round round = new Round();
        round.hit(11);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_if_hit_score_smaller_than_0() throws RuntimeException {
        Round round = new Round();
        round.hit(-1);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_if_hit_three_times_in_round() throws Exception {
        normalHit1and4.hit(5);
    }
}
