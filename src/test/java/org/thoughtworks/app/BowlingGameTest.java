package org.thoughtworks.app;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BowlingGameTest {
    @Test
    public void should_make_round_if_get_hit() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();
        assertThat(bowlingGame.currentRound(), is(1));
        bowlingGame.hit(4);
        bowlingGame.hit(5);
        assertThat(bowlingGame.currentRound(), is(2));
        bowlingGame.hit(10);
        assertThat(bowlingGame.currentRound(), is(3));
        bowlingGame.hit(4);
        assertThat(bowlingGame.currentRound(), is(3));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_if_hit_score_not_in_0_and_10() throws RuntimeException {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.hit(11);
        bowlingGame.hit(-1);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_if_two_hits_more_than_10() throws RuntimeException {
        BowlingGame bowlingGame =new BowlingGame();
        bowlingGame.hit(7);
        bowlingGame.hit(7);
    }
}
