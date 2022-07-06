package coding.secondbiggernumber.utils;

import lombok.Data;

@Data
public class NumbersPair {
    Integer biggest;
    Integer secondBiggest;

    public NumbersPair(Integer a, Integer b) {
        if (a >= b) {
            biggest = a;
            secondBiggest = b;
        } else {
            biggest = b;
            secondBiggest = a;
        }
    }

    public void updatePair(int a) {
        if (biggest < a) {
            secondBiggest = biggest;
            biggest = a;
        } else if (secondBiggest < a && biggest > a) {
            secondBiggest = a;
        } else if (biggest == a) {
            secondBiggest = a;
        }
    }

}
