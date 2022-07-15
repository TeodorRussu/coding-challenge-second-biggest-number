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
        Assertions.assertThatThrownBy(() -> validator.validate(null)).hasMessage(InputValidator.INPUT_NULL_OR_EMPTY_MESSAGE);
        Assertions.assertThatThrownBy(() -> validator.validate(new ArrayList<>())).hasMessage(InputValidator.INPUT_NULL_OR_EMPTY_MESSAGE);
        Assertions.assertThatThrownBy(() -> validator.validate(Collections.singletonList(1))).hasMessage(InputValidator.MINIMUM_OF_2_VALID_VALUES_MESSAGE);
    }

}