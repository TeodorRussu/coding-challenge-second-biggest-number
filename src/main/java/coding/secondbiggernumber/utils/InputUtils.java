package coding.secondbiggernumber.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class InputUtils {
    public static List<Integer> generateListSplittingIndexes(Integer availableProcessors, int listSize) {
        var subsequencesSize = listSize / availableProcessors;
        var index = 0;

        List<Integer> indexes = new ArrayList<>(subsequencesSize);
        while (index <= listSize) {
            indexes.add(index);
            index += subsequencesSize;
        }
        indexes.set(indexes.size() - 1, listSize);
        return indexes;
    }

    public static void removeNullsFromListHead(List<Integer> input) {
        input.removeAll(Collections.singleton(null));
    }
}
