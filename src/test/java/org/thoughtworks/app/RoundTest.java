package org.thoughtworks.app;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RoundTest {
    private Round normalHit1and4;
    private Round spareHit5and5;

    @Before
    public void SetUp() {
        int hitFour = 4;
        int hitOne = 1;
        int hitFive = 5;
        normalHit1and4 = new Round(hitOne);
        normalHit1and4.secondHit(hitFour);
        spareHit5and5 = new Round(hitFive);
        spareHit5and5.secondHit(hitFive);
    }

    @Test
    public void should_return_the_sum_of_two_hits() {
        assertThat(normalHit1and4.getScore(), is(5));
        assertThat(spareHit5and5.getScore(), is(10));
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
        Round strike = new Round(Round.TOTAL_BOTTLE_NUM);
        assertThat(strike.isStrike(), is(true));
        assertThat(spareHit5and5.isStrike(), is(false));
    }

    @Test
    public void should_return_true_for_is_strike_streak_if_hit_all_down_continuous() throws Exception {
        assertStrikeStreak(new Round(Round.TOTAL_BOTTLE_NUM), new Round(Round.TOTAL_BOTTLE_NUM), true);
    }

    @Test
    public void should_return_false_for_is_strike_streak_if_not_hit_all_down() throws Exception {
        assertStrikeStreak(normalHit1and4, new Round(Round.TOTAL_BOTTLE_NUM), false);
    }

    private void assertStrikeStreak(Round currentRound, Round nextRound, boolean result) {
        assertThat(currentRound.isStrikeStreak(nextRound), is(result));
    }
}
