package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.Record;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    RandomIntGenerator randomIntGenerator;

    @Before
    public void init(){
        randomIntGenerator = new RandomIntGenerator();
    }

    @Test
    public void test_create_answer(){
        String str = randomIntGenerator.generateNums(9, 4);
        Answer excepted = new Answer();
        excepted.setNumList(Arrays.asList(str.split(" ")));
        Answer answer = Answer.createAnswer(str);
        assertThat(excepted.toString(), equalTo(answer.toString()));
    }

    @Test
    public void test_check_while_0_correct_and_0_wrong_position() {
        Answer answer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");
        Record record = answer.check(inputAnswer);

        int[] excepted = {0, 0};
        assertThat(excepted, equalTo(record.getValue()));
    }

    @Test
    public void test_check_while_1_correct_and_0_wrong_position() {
        Answer answer = Answer.createAnswer("1 2 3 4");
        Record record = answer.check(Answer.createAnswer("5 6 7 4"));

        int[] excepted = {1, 0};
        assertThat(excepted, equalTo(record.getValue()));
    }

    @Test
    public void test_check_while_0_correct_and_4_wrong_position() {
        Answer answer = Answer.createAnswer("1 2 3 4");
        Record record = answer.check(Answer.createAnswer("4 3 2 1"));

        int[] excepted = {0, 4};
        assertThat(excepted, equalTo(record.getValue()));
    }

    @Test
    public void test_check_while_4_correct_and_0_wrong_position() {
        Answer answer = Answer.createAnswer("1 2 3 4");
        Record record = answer.check(Answer.createAnswer("1 2 3 4"));

        int[] excepted = {4, 0};
        assertThat(excepted, equalTo(record.getValue()));
    }

}