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
    public void should_return_the_sum_of_two_hits(){
        int firstHit = 1;
        int secondHit = 4;
        Round round = new Round(1,4);
        assertThat(round.hitDown(),is(5));
    }
}
