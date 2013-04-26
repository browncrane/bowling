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
        assertThat(bowlingGame.currentRoundNumber(), is(1));
        bowlingGame.hit(4);
        bowlingGame.hit(5);
        assertThat(bowlingGame.currentRoundNumber(), is(2));
        bowlingGame.hit(10);
        assertThat(bowlingGame.currentRoundNumber(), is(3));
        bowlingGame.hit(4);
        assertThat(bowlingGame.currentRoundNumber(), is(3));
    }

    @Test
    public void should_return_true_for_game_over_if_final_round_not_strike_or_spare() throws Exception {
        bowlingGameWith9Round.hit(5);
        bowlingGameWith9Round.hit(4);
        assertThat(bowlingGameWith9Round.gameOver(), is(true));
    }

    @Test
    public void should_return_false_for_game_over_if_final_round_is_spare() throws Exception {
        bowlingGameWith9Round.hit(6);
        bowlingGameWith9Round.hit(4);
        assertThat(bowlingGameWith9Round.gameOver(), is(false));
    }

    @Test
    public void should_return_false_for_game_over_if_final_round_is_strike() throws Exception {
        bowlingGameWith9Round.hit(10);
        assertThat(bowlingGameWith9Round.gameOver(), is(false));
    }

    @Test(expected = RuntimeException.class)
    public void should_thrown_exception_if_hit_after_game_end() throws Exception {
        bowlingGameWith9Round.hit(5);
        bowlingGameWith9Round.hit(4);
        bowlingGameWith9Round.hit(3);
    }

    @Test
    public void should_have_2_additional_hits_if_final_round_is_strike() throws Exception {
        bowlingGameWith9Round.hit(10);
        assertThat(bowlingGameWith9Round.additionalHit(), is(2));
    }

    @Test
    public void should_have_1_additional_hit_if_final_round_is_spare() throws Exception {
        bowlingGameWith9Round.hit(6);
        bowlingGameWith9Round.hit(4);
        assertThat(bowlingGameWith9Round.additionalHit(), is(1));
    }

    @Test
    public void should_score_for_final_round_not_spare_or_strike() throws Exception {
        bowlingGameWith9Round.hit(6);
        bowlingGameWith9Round.hit(3);
        assertThat(bowlingGameWith9Round.getScoreByRound(10), is(108));
    }

    @Test
    public void should_score_for_final_round_is_spare() throws Exception {
        bowlingGameWith9Round.hit(6);
        bowlingGameWith9Round.hit(4);
        bowlingGameWith9Round.hit(1);
        assertThat(bowlingGameWith9Round.getScoreByRound(10), is(110));
    }

    @Test
    public void should_score_for_final_round_is_strike() throws Exception {
        bowlingGameWith9Round.hit(10);
        bowlingGameWith9Round.hit(4);
        bowlingGameWith9Round.hit(5);
        assertThat(bowlingGameWith9Round.getScoreByRound(10), is(118));
    }

    @Test
    public void should_score_for_final_round_is_strike_streak() throws Exception {
        bowlingGameWith9Round.hit(10);
        bowlingGameWith9Round.hit(10);
        bowlingGameWith9Round.hit(1);
        assertThat(bowlingGameWith9Round.getScoreByRound(10), is(120));
    }
}
