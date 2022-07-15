package coding.secondbiggernumber.core;

import coding.secondbiggernumber.validator.InputValidator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static coding.secondbiggernumber.validator.InputValidator.MINIMUM_OF_2_VALID_VALUES_MESSAGE;

@Service
@Data
@Slf4j
public class SecondBiggestNumberFinder {

    @Autowired
    InputValidator inputValidator;

    public Integer findSecondBiggestNumber(LinkedList<Integer> input) throws ExecutionException, InterruptedException {
        inputValidator.validate(input);
        return findSecondBiggerNumberParallel(input);
    }

    private Integer findSecondBiggerNumberParallel(LinkedList<Integer> input) {
        Integer theBiggestNumber = getTheBiggestNumber(input);
        input.remove(theBiggestNumber);
        return getTheBiggestNumber(input);
    }

    private Integer getTheBiggestNumber(LinkedList<Integer> input) {
        return input.parallelStream()
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException(MINIMUM_OF_2_VALID_VALUES_MESSAGE));
    }
}
