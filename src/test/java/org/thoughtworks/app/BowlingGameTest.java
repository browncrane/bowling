package org.thoughtworks.app;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BowlingGameTest {

    private BowlingGame bowlingGameWith9Round;

    @Before
    public void setUp() throws Exception {
        bowlingGameWith9Round = new BowlingGame();
        bowlingGameWith9Round.hit(9);
        bowlingGameWith9Round.hit(0);
        bowlingGameWith9Round.hit(9);
        bowlingGameWith9Round.hit(0);
        bowlingGameWith9Round.hit(10);
        bowlingGameWith9Round.hit(9);
        bowlingGameWith9Round.hit(0);
        bowlingGameWith9Round.hit(9);
        bowlingGameWith9Round.hit(0);
        bowlingGameWith9Round.hit(5);
        bowlingGameWith9Round.hit(5);
        bowlingGameWith9Round.hit(9);
        bowlingGameWith9Round.hit(0);
        bowlingGameWith9Round.hit(9);
        bowlingGameWith9Round.hit(0);
        bowlingGameWith9Round.hit(4);
        bowlingGameWith9Round.hit(3);
    }

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
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.hit(7);
        bowlingGame.hit(7);
    }

    @Test
    public void should_return_true_for_game_end_if_round_10_no_spare_or_strike() throws Exception {
        bowlingGameWith9Round.hit(3);
        bowlingGameWith9Round.hit(4);
        assertThat(bowlingGameWith9Round.gameEnd(), is(true));
    }

    @Test
    public void should_return_one_if_round_10_is_spare() throws Exception {
        bowlingGameWith9Round.hit(6);
        bowlingGameWith9Round.hit(4);
        assertThat(bowlingGameWith9Round.additionalHit(), is(1));
    }

    @Test
    public void should_return_two_round_if_round_10_is_strike() throws Exception {
        bowlingGameWith9Round.hit(10);
        assertThat(bowlingGameWith9Round.additionalHit(), is(2));
    }

    @Test(expected = RuntimeException.class)
    public void should_thrown_exception_if_hit_after_game_end() throws Exception {
        assertThat(bowlingGameWith9Round.currentRound(), is(10));
        bowlingGameWith9Round.hit(5);
        bowlingGameWith9Round.hit(4);
        assertThat(bowlingGameWith9Round.gameEnd(), is(true));
        bowlingGameWith9Round.hit(3);
    }

//    @Test
    public void should_have_1_additional_hit_if_round_10_is_spare() throws Exception {
        bowlingGameWith9Round.hit(6);
        bowlingGameWith9Round.hit(4);
        assertThat(bowlingGameWith9Round.gameEnd(), is(false));
        bowlingGameWith9Round.hit(5);
        assertThat(bowlingGameWith9Round.getScoreByRound(10), is(114));
    }
}
