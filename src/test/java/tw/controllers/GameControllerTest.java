package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;
import static tw.core.GameStatus.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void test_success() throws OutOfRangeAnswerException, IOException {

        Answer actualAnswer = Answer.createAnswer("1 2 3 4");

        Answer inputAnswer1 = Answer.createAnswer("2 3 4 5");
        Answer inputAnswer2 = Answer.createAnswer("1 2 3 4");

        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);

        Game game = new Game(answerGenerator);
        GameView gameView = new GameView();
        GameController gameController = new GameController(game, gameView);

        InputCommand inputCommand = mock(InputCommand.class);
        when(inputCommand.input()).thenReturn(inputAnswer1, inputAnswer2);

        gameController.beginGame();
        gameController.play(inputCommand);
        String expect = "------Guess Number Game, You have 6 chances to guess!  ------\r\n";
        expect += "Guess Result: " + game.guess(inputAnswer1).getResult()+"\r\n";
        expect += "Guess History:\r\n";
        expect += "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n";
        expect += "Guess Result: " + game.guess(inputAnswer2).getResult()+"\r\n";
        expect += "Guess History:\r\n";
        expect += "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n";
        expect += "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\r\n";
        expect += "Game Status: " + SUCCESS + "\r\n";

        assertThat(expect, equalTo(outContent.toString()));
    }

    @Test
    public void test_fail() throws OutOfRangeAnswerException, IOException {

        Answer actualAnswer = Answer.createAnswer("1 2 3 4");

        Answer[] inputAnswer = new Answer[6];
        for (int i = 0; i < inputAnswer.length; i++) {
            inputAnswer[i] = Answer.createAnswer("2 3 4 5");
        }

        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);

        Game game = new Game(answerGenerator);
        GameView gameView = new GameView();
        GameController gameController = new GameController(game, gameView);

        InputCommand inputCommand = mock(InputCommand.class);
        when(inputCommand.input()).thenReturn(inputAnswer[0], inputAnswer);

        gameController.beginGame();
        gameController.play(inputCommand);
        String expect = "------Guess Number Game, You have 6 chances to guess!  ------\r\n";
        for (int i = 0; i < 6; i++) {
            expect += "Guess Result: " + game.guess(inputAnswer[i]).getResult()+"\r\n";
            expect += "Guess History:\r\n";
            for (int j = 0; j <= i; j++) {
                expect += "[Guess Numbers: 2 3 4 5, Guess Result: 0A3B]\r\n";
            }
        }
        expect += "Game Status: " + FAIL + "\r\n";

        assertThat(expect, equalTo(outContent.toString()));
    }
}