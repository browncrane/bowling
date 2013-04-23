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

}
