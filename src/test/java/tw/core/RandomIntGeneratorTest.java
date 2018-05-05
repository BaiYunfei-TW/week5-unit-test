package tw.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;
import tw.validator.InputValidator;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.fail;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    public RandomIntGenerator randomIntGenerator;
    public InputValidator inputValidator;

    @Before
    public void init() {
        this.randomIntGenerator = new RandomIntGenerator();
        this.inputValidator = new InputValidator();
    }

    @Test
    public void test_generate_random_int_list() {
        String numStr = randomIntGenerator.generateNums(9, 4);
        assert inputValidator.validate(numStr);
    }

    @Test
    public void test_generate_while_digitmax_is_greater_than_numbers_of_need() {
        int digitmax = 9;
        int numbersOfNeed = 10;
        try {
            randomIntGenerator.generateNums(digitmax, numbersOfNeed);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), equalTo("Can't ask for more numbers than are available"));
            return;
        }
        fail(String.format("expected IllegalArgumentException for digitmax(%d) greater than numbers of need(%d)", digitmax, numbersOfNeed));
    }
}