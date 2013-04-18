package org.thoughtworks.app;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: crane
 * Date: 13-4-15
 * Time: 下午8:30
 */
public class RoundTest {

    private Round normal;
    private Round spare;

    @Before
    public void SetUp() {
        int hitFour = 4;
        int hitOne = 1;
        int hitFive = 5;
        normal = new Round(hitOne, hitFour);
        spare = new Round(hitFive, hitFive);
    }

    @Test
    public void should_return_the_sum_of_two_hits() {
        assertThat(normal.hitDownAll(), is(5));
        assertThat(spare.hitDownAll(), is(10));
    }

    @Test
    public void should_return_true_if_the_round_is_spare() throws Exception {
        assertThat(spare.isSpare(), is(true));
        assertThat(normal.isSpare(), is(false));
    }

    @Test
    public void should_return_first_hit_score() throws Exception {
        assertThat(normal.getFirstHit(), is(1));
        assertThat(spare.getFirstHit(), is(5));
    }
}
