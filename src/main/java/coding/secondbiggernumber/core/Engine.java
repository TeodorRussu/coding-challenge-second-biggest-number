package coding.secondbiggernumber.core;

import coding.secondbiggernumber.utils.NumbersPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class Engine {

    @Async
    public CompletableFuture<NumbersPair> getNumbersPairAsyncFromInputChunks(List<Integer> input, Integer firstIndex, Integer lastIndex) {
        log.info("starting async job. thread: " + Thread.currentThread().getName());
        NumbersPair biggerNumbersPairFromList = getBiggestNumbersPairFromList(input.subList(firstIndex, lastIndex));
        return CompletableFuture.completedFuture(biggerNumbersPairFromList);
    }

    public NumbersPair getBiggestNumbersPairFromList(List<Integer> numbers) {
        var largestNumbersPair = new NumbersPair(numbers.get(0), numbers.get(1));

        for (int i = 2; i < numbers.size(); i++) {
            largestNumbersPair.updatePair(numbers.get(i));
        }
        return largestNumbersPair;
    }
}
