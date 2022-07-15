package coding.secondbiggernumber;

import coding.secondbiggernumber.core.SecondBiggestNumberFinder;
import coding.secondbiggernumber.validator.InputValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SecondBiggestNumberFinderTest {

    SecondBiggestNumberFinder secondBiggestNumberFinder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        secondBiggestNumberFinder = new SecondBiggestNumberFinder();
        secondBiggestNumberFinder.setInputValidator(new InputValidator());
    }

    @Test
    void findSecondBiggestNumber_nullsInTheInput_ignoreThem() throws ExecutionException, InterruptedException {
        Integer nullInteger = null;
        LinkedList<Integer> input = new LinkedList<>();
        input.add(1);
        input.add(0);
        input.add(nullInteger);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(0, output);
    }

    @Test
    void findSecondBiggestNumber_nullFirstItemInInput_ignoreIt() throws ExecutionException, InterruptedException {
        Integer nullInteger = null;
        LinkedList<Integer> input = new LinkedList<>();
        input.add(nullInteger);
        input.add(1);
        input.add(0);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(0, output);
    }

    @Test
    void findSecondBiggestNumber_firstTwoItemsInInputAreNulls_ignoreThem() throws ExecutionException, InterruptedException {
        Integer nullInteger = null;
        LinkedList<Integer> input = new LinkedList<>();
        input.add(nullInteger);
        input.add(nullInteger);
        input.add(1);
        input.add(0);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(0, output);
    }

    @Test
    void findSecondBiggestNumber_twoDifferentNumbers() throws ExecutionException, InterruptedException {
        var input = generateListOf(0, 1);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(0, output);
    }

    @Test
    void findSecondBiggestNumber_twoSimilarNumbers() throws ExecutionException, InterruptedException {
        var input = generateListOf(1, 1);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(1, output);
    }

    @Test
    void findSecondBiggestNumber_allSimilarNumbers() throws ExecutionException, InterruptedException {
        var input = generateListOf(1, 1, 1, 1, 1);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(1, output);
    }

    @Test
    void findSecondBiggestNumber_negativeNumbers() throws ExecutionException, InterruptedException {
        var input = generateListOf(-1, -2, -3, -1, -1);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(-1, output);
    }

    @Test
    void findSecondBiggestNumber_integerMinMax() throws ExecutionException, InterruptedException {
        var input = generateListOf(Integer.MIN_VALUE, Integer.MAX_VALUE);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(Integer.MIN_VALUE, output);
    }

    @Test
    void findSecondBiggestNumber_integerMaxMax() throws ExecutionException, InterruptedException {
        var input = generateListOf(Integer.MAX_VALUE, Integer.MAX_VALUE);
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(Integer.MAX_VALUE, output);
    }

    @Test
    void findSecondBiggestNumber_uniqueNumbers() throws ExecutionException, InterruptedException {
        var input = IntStream.rangeClosed(1, 100_000).boxed().collect(Collectors.toCollection(LinkedList::new));
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertNotNull(output);
        Assertions.assertEquals(99_999, output);
    }

    @Test
    void findSecondBiggestNumber_maximumIsDuplicated() throws ExecutionException, InterruptedException {
        int inputSize = 1_000_000;
        var input = IntStream.rangeClosed(0, inputSize).boxed().collect(Collectors.toCollection(LinkedList::new));
        input.add(0, inputSize); //duplicate the max value in the input list

        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        Assertions.assertEquals(inputSize, output);
    }

    private LinkedList<Integer> generateListOf(Integer... values) {
        return new LinkedList<>(Arrays.asList(values));
    }
}