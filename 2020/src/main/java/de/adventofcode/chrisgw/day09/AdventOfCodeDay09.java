package de.adventofcode.chrisgw.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


/**
 * https://adventofcode.com/2020/day/9
 */
public class AdventOfCodeDay09 {

    private final int preambleLength;
    private final List<Integer> numbers;


    public AdventOfCodeDay09(int preambleLength) {
        this.preambleLength = preambleLength;
        this.numbers = new ArrayList<>(preambleLength);
    }


    public boolean readNext(int number) {
        if (!isValidNumber(number)) {
            return false;
        }
        numbers.add(number);
        return true;
    }


    private boolean isValidNumber(int number) {
        return numbers.size() < preambleLength //
                || preambleNumbers().anyMatch(firstNumber -> containsPreambleSumPair(number, firstNumber));
    }

    private Stream<Integer> preambleNumbers() {
        int fromIndex = numbers.size() - preambleLength;
        return numbers.subList(fromIndex, numbers.size()).stream();
    }

    private boolean containsPreambleSumPair(int sum, int firstNumber) {
        int secondNumber = sum - firstNumber;
        return firstNumber != secondNumber && preambleNumbers().anyMatch(n -> n == secondNumber);
    }


    // part 02

    public int findContigousListForInvalidNumber(int firstInvalidNumber) {
        for (int fromIndex = 0; fromIndex < numbers.size() - 1; fromIndex++) {
            for (int toIndex = fromIndex + 2; toIndex < numbers.size() - 1; toIndex++) {
                List<Integer> listForInvalidNumber = numbers.subList(fromIndex, toIndex);
                int sum = listForInvalidNumber.stream().reduce(0, Integer::sum);
                if (sum == firstInvalidNumber) {
                    int min = listForInvalidNumber.stream().min(Integer::compareTo).orElseThrow();
                    int max = listForInvalidNumber.stream().max(Integer::compareTo).orElseThrow();
                    return min + max;
                } else if (sum > firstInvalidNumber) {
                    break;
                }
            }
        }
        return -1;
    }

}
