package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        int[] values = {3, 5, 1, 4, 2, 3};
        System.out.println(minValue(values));

        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        System.out.println(oddOrEven(integers));
    }

    static int minValue(int[] values) {
        final int[] result = new int[1];
        final int[] length = {(int) Arrays.stream(values)
                .distinct()
                .count() - 1};
        Arrays.stream(values)
                .distinct()
                .sorted()
                .forEach(value -> {
                    result[0] = result[0] + value * ((int) Math.pow(10, length[0]));
                    length[0] = length[0] - 1;
                });
        return result[0];
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        final int[] sum = new int[1];
        List<Integer> list = integers.stream()
                .peek(integer -> sum[0] = sum[0] + integer)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        if (sum[0] % 2 == 0) {
                            if (integer % 2 == 0) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            if (integer % 2 == 0) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                    }
                })
                .collect(Collectors.toList());
        return list;
    }
}
