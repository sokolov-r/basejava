package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] values = {3, 5, 1, 4, 2, 3, 5};
        System.out.println(minValue(values));

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(oddOrEven(integers));
    }

    static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (left, right) -> left * 10 + right);
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        int s = 0;
        for (Integer i : integers) {
            s = s + i;
        }

        final int sum = s;
        List<Integer> list;
        if (sum % 2 == 0) {
            list = integers.stream()
                    .filter(integer -> integer % 2 == 0)
                    .collect(Collectors.toList());
        } else {
            list = integers.stream()
                    .filter(integer -> integer % 2 != 0)
                    .collect(Collectors.toList());
        }
        return list;
    }
}
