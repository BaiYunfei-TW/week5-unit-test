package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    InputValidator validator;

    @Before
    public void init() {
        validator = new InputValidator();
    }

    @Test
    public void test_validate_single_digit_with_wrong_number_counts() {
        String numString = "1 2 3";
        boolean result = validator.validate(numString);
        boolean excepted = false;

        assertThat(excepted, equalTo(result));
    }

    @Test
    public void test_validate_single_digit_with_wrong_number_format() {
        String numString = "1 2 3 11";
        boolean result = validator.validate(numString);
        boolean excepted = false;

        assertThat(excepted, equalTo(result));
    }

    @Test
    public void test_validate_single_digit_with_both_wrong_number_counts_and_number_format() {
        String numString = "1 2 30";
        boolean result = validator.validate(numString);
        boolean excepted = false;

        assertThat(excepted, equalTo(result));
    }

    @Test
    public void test_validate_single_digit_with_correct_number_format_and_counts() {
        String numString = "1 2 3 4";
        boolean result = validator.validate(numString);
        boolean excepted = true;

        assertThat(excepted, equalTo(result));
    }

}
