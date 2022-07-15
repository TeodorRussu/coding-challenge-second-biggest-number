package coding.secondbiggernumber.validator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InputValidator {

    public static final String INPUT_NULL_OR_EMPTY_MESSAGE = "the input is null or empty";
    public static final String MINIMUM_OF_2_VALID_VALUES_MESSAGE = "the input is does not contain the minimum of 2 values";

    public void validate(List<Integer> input) {
        if (input == null || input.isEmpty()) {
            throw new RuntimeException(INPUT_NULL_OR_EMPTY_MESSAGE);
        }
        if (input.size() < 2) {
            throw new RuntimeException(MINIMUM_OF_2_VALID_VALUES_MESSAGE);
        }
    }


}
