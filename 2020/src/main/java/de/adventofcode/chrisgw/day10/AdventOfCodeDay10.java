package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;


/**
 * https://adventofcode.com/2020/day/10
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzle {

    private final SortedSet<JoltAdapter> aviableJoltAdapters;
    private final JoltAdapter outputJoltage = new JoltAdapter(0);
    private final JoltAdapter deviceJoltage;


    public AdventOfCodeDay10(List<String> inputLines) {
        super(inputLines);
        aviableJoltAdapters = inputLines.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(JoltAdapter::new)
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        int maxRatedJolts = aviableJoltAdapters.last().getRatedJolts();
        deviceJoltage = new JoltAdapter(maxRatedJolts + 3);
    }


    @Override
    public Number solveFirstPart() {
        int joltDifference1 = 0;
        int joltDifference3 = 0;
        JoltAdapter currentJoltAdapter = outputJoltage;
        for (JoltAdapter nextJoltAdapter : aviableJoltAdapters) {
            int joltsDifference = nextJoltAdapter.joltsDifferenceTo(currentJoltAdapter);
            if (joltsDifference == 1) {
                joltDifference1++;
            } else if (joltsDifference == 3) {
                joltDifference3++;
            }
            currentJoltAdapter = nextJoltAdapter;
        }
        joltDifference3++;
        return joltDifference1 * joltDifference3;
    }


    @Override
    public Object solveSecondPart() {
        return null;
    }


    @Override
    public Year getYear() {
        return Year.of(2020);
    }

    @Override
    public int getDay() {
        return 10;
    }

}
