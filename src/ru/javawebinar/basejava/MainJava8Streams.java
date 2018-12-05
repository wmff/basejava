package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainJava8Streams {

    public static void main(String[] args) {
        System.out.println("minValue:");
        int[] values1 = {1, 2, 3, 3, 2, 3};
        System.out.println("in: " + Arrays.toString(values1) + " out: " + minValue(values1));

        int[] values2 = {9, 8};
        System.out.println("in: " + Arrays.toString(values2) + " out: " + minValue(values2));

        System.out.println();
        System.out.println("oddOrEven:");
        List<Integer> values3 = Arrays.asList(1, 2, 3);
        System.out.println("in: " + values3.toString() + " out: " + oddOrEven(values3));

        List<Integer> values4 = Arrays.asList(1, 2, 4);
        System.out.println("in: " + values4.toString() + " out: " + oddOrEven(values4));

    }

    private static Integer minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((left, right) -> left * 10 + right)
                .orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int oddOrEven = integers.stream().mapToInt(Integer::intValue).sum() % 2;
        return integers.stream()
                .filter(x -> (oddOrEven != 0) == (x % 2 == 0))
                .collect(Collectors.toList());
    }
}
