package tw.core.generator;

import org.junit.Test;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    @Test
    public void test_generate_answer() throws OutOfRangeAnswerException {
        String excepted = "1 2 3 4";

        RandomIntGenerator randomIntGenerator = mock(RandomIntGenerator.class);
        when(randomIntGenerator.generateNums(9, 4)).thenReturn(excepted);

        Answer answer = new AnswerGenerator(randomIntGenerator).generate();
        assertThat(excepted, equalTo(answer.toString()));
    }
}

