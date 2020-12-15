package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * https://adventofcode.com/2020/day/15
 */
public class AdventOfCodeDay15 extends AdventOfCodePuzzle {

    private List<Integer> spokenNumbers;

    public AdventOfCodeDay15(List<Integer> startingNumbers) {
        super(Year.of(2020), 15,
                List.of(startingNumbers.stream().map(String::valueOf).collect(Collectors.joining(","))));
        spokenNumbers = new ArrayList<>(startingNumbers);
    }


    @Override
    public Integer solveFirstPart() {
        while (!spokenNumbers.isEmpty() && spokenNumbers.size() < 2020) {
            int lastNumber = getLastNumber();
            int lastSpokenNumber = findSpokenNumber(lastNumber);
            spokenNumbers.add(lastSpokenNumber);
        }
        return getLastNumber();
    }

    private int getLastNumber() {
        return spokenNumbers.get(spokenNumbers.size() - 1);
    }

    private int findSpokenNumber(int lastNumber) {
        for (int i = spokenNumbers.size() - 2; i >= 0; i--) {
            int number = spokenNumbers.get(i);
            if (number == lastNumber) {
                return spokenNumbers.size() - i - 1;
            }
        }
        return 0;
    }


    // part 02

    @Override
    public Number solveSecondPart() {
        return 0;
    }

}
