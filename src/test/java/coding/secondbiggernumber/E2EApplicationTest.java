package coding.secondbiggernumber;

import coding.secondbiggernumber.core.SecondBiggestNumberFinder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
class E2EApplicationTest {

    @Autowired
    SecondBiggestNumberFinder secondBiggestNumberFinder;

    @Test
    void e2eTest() throws ExecutionException, InterruptedException {
        int inputSize = 1_000_000;
        var input = IntStream.rangeClosed(0, inputSize).boxed().collect(Collectors.toCollection(LinkedList::new));
        input.add(0, inputSize);

        LocalDateTime start = LocalDateTime.now();
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        LocalDateTime end = LocalDateTime.now();
        log.info("async execution time: " + ChronoUnit.MILLIS.between(start, end));
        Assertions.assertEquals(inputSize, output);
    }

}
