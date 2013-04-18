package org.thoughtworks.app;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: crane
 * Date: 13-4-15
 * Time: 下午8:30
 */
public class RoundTest {
    @Test
    public void should_return_the_sum_of_two_hits() {
        int firstHit = 1;
        int secondHit = 4;
        Round round = new Round(firstHit, secondHit);
        assertThat(round.hitDownAll(), is(5));
    }

    @Test
    public void should_return_true_if_the_round_is_spare() throws Exception {
        int firstHit = 5;
        int secondHit = 5;
        int hitThree = 3;
        Round spareRound = new Round(firstHit, secondHit);
        assertThat(spareRound.isSpare(),is(true));
        Round normalRound = new Round(firstHit,hitThree);
        assertThat(normalRound.isSpare(),is(false));
    }
}
