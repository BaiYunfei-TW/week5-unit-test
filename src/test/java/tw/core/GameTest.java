package tw.core;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito.*;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static tw.core.GameStatus.*;

public class GameTest {

    AnswerGenerator answerGenerator;
    Answer actualAnswer;
    Game game;

    @Before
    public void init() throws OutOfRangeAnswerException {
        actualAnswer = Answer.createAnswer("1 2 3 4");

        answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);

        game = new Game(answerGenerator);
    }

    @Test
    public void test_guess_when_input_correct_result() {
        GuessResult result = game.guess(Answer.createAnswer("1 2 3 4"));

        String expect = "4A0B";
        assertThat(expect, equalTo(result.getResult()));
    }

    @Test
    public void test_guess_when_input_1_correct_and_1_wrong_position_result() {
        GuessResult result = game.guess(Answer.createAnswer("1 3 5 6"));

        String expect = "1A1B";
        assertThat(expect, equalTo(result.getResult()));
    }

    @Test
    public void test_check_status_and_return_continue() {
        game.guess(Answer.createAnswer("1 3 5 6"));

        String expect = CONTINUE;
        assertThat(expect, equalTo(game.checkStatus()));
    }

    @Test
    public void test_check_status_and_return_success() {
        game.guess(Answer.createAnswer("1 3 5 6"));
        game.guess(Answer.createAnswer("1 2 3 4"));

        String expect = SUCCESS;
        assertThat(expect, equalTo(game.checkStatus()));
    }

    @Test
    public void test_check_status_and_return_fail() {
        for (int i = 0; i < 10; i++) {
            game.guess(Answer.createAnswer("1 3 5 6"));
        }

        String expect = FAIL;
        assertThat(expect, equalTo(game.checkStatus()));
    }

    @Test
    public void test_check_continue_and_should_return_true() {
        game.guess(Answer.createAnswer("1 2 3 5"));

        assertThat(game.checkCoutinue(), equalTo(true));
    }

    @Test
    public void test_check_continue_when_success_and_should_return_false() {
        game.guess(Answer.createAnswer("1 2 3 4"));

        assertThat(game.checkCoutinue(), equalTo(false));
    }

    @Test
    public void test_check_continue_when_failed_and_should_return_false() {
        for (int i = 0; i < 10; i++) {
            game.guess(Answer.createAnswer("1 2 3 4"));
        }

        assertThat(game.checkCoutinue(), equalTo(false));
    }

}
