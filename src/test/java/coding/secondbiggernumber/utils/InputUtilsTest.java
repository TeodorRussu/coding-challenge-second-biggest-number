package coding.secondbiggernumber.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class InputUtilsTest {

    @Test
    void generateListSplittingIndexes_listSizeIsMultipleOfAvailableProcessors() {
        var output = InputUtils.generateListSplittingIndexes(16, 100);
        var expectedOutput = List.of(0, 6, 12, 18, 24, 30, 36, 42, 48, 54, 60, 66, 72, 78, 84, 90, 100);
        Assertions.assertThatList(expectedOutput).isSorted();
        Assertions.assertThatList(output).isEqualTo(expectedOutput);
    }

    @Test
    void generateListSplittingIndexes_listSizeIsNotMultipleOfAvailableProcessors() {
        var output = InputUtils.generateListSplittingIndexes(5, 100);
        var expectedOutput = List.of(0, 20, 40, 60, 80, 100);

        Assertions.assertThatList(expectedOutput).isSorted();
        Assertions.assertThatList(output).isEqualTo(expectedOutput);
    }

    @Test
    void inputContainsNullsSpreadAcrossIt_removeThemAll() {
        Integer nullInteger = null;
        List<Integer> input = new ArrayList<>();
        input.add(nullInteger);
        input.add(1);
        input.add(nullInteger);
        input.add(0);
        input.add(0);
        input.add(nullInteger);
        input.add(3);
        InputUtils.removeNullsFromList(input);
        Assertions.assertThat(input).hasSize(4);
        Assertions.assertThatList(input).doesNotContainNull();
    }

    @Test
    void inputContainsOnlyNulls_removeThemAll() {
        Integer nullInteger = null;
        List<Integer> input = new ArrayList<>();
        input.add(nullInteger);
        input.add(nullInteger);
        input.add(nullInteger);
        input.add(nullInteger);
        InputUtils.removeNullsFromList(input);
        Assertions.assertThat(input.isEmpty());
        Assertions.assertThatList(input).doesNotContainNull();
    }

}