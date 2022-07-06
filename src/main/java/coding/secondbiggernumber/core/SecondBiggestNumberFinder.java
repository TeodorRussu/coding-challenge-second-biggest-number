package coding.secondbiggernumber.core;

import coding.secondbiggernumber.utils.InputUtils;
import coding.secondbiggernumber.utils.NumbersPair;
import coding.secondbiggernumber.utils.RuntimeExplorer;
import coding.secondbiggernumber.validator.InputValidator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Data
@Slf4j
public class SecondBiggestNumberFinder {

    @Autowired
    RuntimeExplorer runtimeExplorer;

    @Autowired
    Engine engine;

    @Autowired
    InputValidator inputValidator;


    public Integer findSecondBiggestNumber(List<Integer> input) throws ExecutionException, InterruptedException {
        inputValidator.validateInput(input);

        if (input.size() <= 1000) {
            log.info("the input list is not big. selecting linear flow");
            return findSecondBiggestNumberLinear(input);
        }
        log.info("selecting parallel flow");
        return findSecondBiggerNumberParallel(input);
    }


    public Integer findSecondBiggestNumberLinear(List<Integer> numbers) {
        var largestNumbersPair = engine.getBiggestNumbersPairFromList(numbers);
        return largestNumbersPair.getSecondBiggest();
    }


    private Integer findSecondBiggerNumberParallel(List<Integer> input) throws ExecutionException, InterruptedException {
        var numberOfParallelJobs = runtimeExplorer.getAvailableProcessors();
        var indexesForSublistViews = InputUtils.generateListSplittingIndexes(numberOfParallelJobs, input.size());
        var parallelJobsResults = findShortListNumbersAsync(input, numberOfParallelJobs, indexesForSublistViews);

        return extractSecondBigNumberFromAsyncJobsResults(parallelJobsResults);
    }


    public CompletableFuture<NumbersPair>[] findShortListNumbersAsync(List<Integer> input, Integer availableProcessors, List<Integer> inputSplitIndexes) {
        CompletableFuture<NumbersPair>[] futures = new CompletableFuture[availableProcessors];
        for (int i = 0; i < inputSplitIndexes.size() - 1; i++) {
            var firstIndex = inputSplitIndexes.get(i);
            var lastIndex = inputSplitIndexes.get(i + 1);
            futures[i] = engine.getNumbersPairAsyncFromInputChunks(input, firstIndex, lastIndex);
        }
        CompletableFuture.allOf(futures).join();
        return futures;
    }


    private Integer extractSecondBigNumberFromAsyncJobsResults(CompletableFuture<NumbersPair>[] futures) throws ExecutionException, InterruptedException {
        var finalNumbersPair = futures[0].get();
        for (var future : futures) {
            var jobResult = future.get();
            finalNumbersPair.updatePair(jobResult.getBiggest());
            finalNumbersPair.updatePair(jobResult.getSecondBiggest());
        }
        return finalNumbersPair.getSecondBiggest();
    }

}
