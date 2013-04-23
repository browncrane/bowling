package org.thoughtworks.app;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BowlingGameTest {
    @Test
    public void should_return_round_if_get_hit() throws Exception {
        BowlingGame bowlingGame = new BowlingGame();
        int hitFour = 4;
        int hitFive = 5;
        bowlingGame.firstHitInRound(hitFour);
        bowlingGame.secondHitInRound(hitFive);
        assertThat(bowlingGame.currentRound() != null, is(true));
    }
}
