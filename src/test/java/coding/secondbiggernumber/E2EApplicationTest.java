package coding.secondbiggernumber;

import coding.secondbiggernumber.core.SecondBiggestNumberFinder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        int inputSize = 200_000_000;
        var input = IntStream.rangeClosed(0, inputSize).boxed().collect(Collectors.toList());
        input.add(0, inputSize);

        LocalDateTime start = LocalDateTime.now();
        var output = secondBiggestNumberFinder.findSecondBiggestNumber(input);
        LocalDateTime end = LocalDateTime.now();
        log.info("async execution time: " + ChronoUnit.MILLIS.between(start, end));
        Assertions.assertEquals(inputSize, output);

        var startS = LocalDateTime.now();
        output = secondBiggestNumberFinder.findSecondBiggestNumberLinear(input);
        var endS = LocalDateTime.now();
        log.info("sync execution time: " + ChronoUnit.MILLIS.between(startS, endS));
        Assertions.assertEquals(inputSize, output);
    }

}
