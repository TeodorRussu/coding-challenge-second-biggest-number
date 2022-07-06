package coding.secondbiggernumber.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

class InputValidatorTest {

    InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();
    }

    @Test
    void testInvalidInput() {
        Assertions.assertThatThrownBy(() -> validator.validateInput(null)).hasMessage(InputValidator.INPUT_NULL_OR_EMPTY_MESSAGE);
        Assertions.assertThatThrownBy(() -> validator.validateInput(new ArrayList<>())).hasMessage(InputValidator.INPUT_NULL_OR_EMPTY_MESSAGE);
        Assertions.assertThatThrownBy(() -> validator.validateInput(Collections.singletonList(1))).hasMessage(InputValidator.MINIMUM_OF_2_VALUES_MESSAGE);
    }

}