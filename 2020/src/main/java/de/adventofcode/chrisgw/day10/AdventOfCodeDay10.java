package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * https://adventofcode.com/2020/day/10
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzle {

    private NavigableSet<JoltAdapter> availableJoltAdapters;
    private JoltAdapter deviceJoltage;


    public AdventOfCodeDay10(List<String> inputLines) {
        super(inputLines);
        availableJoltAdapters = inputLines.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(JoltAdapter::new)
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        int maxRatedJolts = availableJoltAdapters.last().getRatedJolts();
        deviceJoltage = new JoltAdapter(maxRatedJolts + 3);
        availableJoltAdapters.add(deviceJoltage);
    }


    @Override
    public Number solveFirstPart() {
        JoltAdapterArrangement joltAdapterArrangement = buildFullJoltAdapterArrangement();
        int joltDifference1 = joltAdapterArrangement.calculateJoltDifferenceSum(1);
        int joltDifference3 = joltAdapterArrangement.calculateJoltDifferenceSum(3);
        return joltDifference1 * joltDifference3;
    }


    @Override
    public Number solveSecondPart() {
        JoltAdapterArrangement joltAdapterArrangement = buildFullJoltAdapterArrangement();
        long sum = 1;
        int distanceOne = 0;
        Iterator<JoltAdapter> iterator = joltAdapterArrangement.iterator();
        JoltAdapter prevJoltAdapter = iterator.next();
        while (iterator.hasNext()) {
            JoltAdapter nextAdapter = iterator.next();
            int difference = nextAdapter.getRatedJolts() - prevJoltAdapter.getRatedJolts();
            if (difference == 1) {
                distanceOne++;
            } else {
                long combinations = tribonacci(distanceOne + 2);
                if (combinations >= 2) {
                    sum *= combinations;
                }
                distanceOne = 0;
            }
            prevJoltAdapter = nextAdapter;
        }
        return sum;
    }

    private JoltAdapterArrangement buildFullJoltAdapterArrangement() {
        JoltAdapterArrangement joltAdapterArrangement = new JoltAdapterArrangement();
        for (JoltAdapter nextJoltAdapter : availableJoltAdapters) {
            joltAdapterArrangement = joltAdapterArrangement.append(nextJoltAdapter);
        }
        return joltAdapterArrangement;
    }


    public Set<JoltAdapterArrangement> buildAllPossibleJoltAdapterArrangements(
            JoltAdapterArrangement fromAdapterArrangement) {
        JoltAdapter lastJoltAdapter = fromAdapterArrangement.lastJoltAdapter();
        if (lastJoltAdapter.equals(deviceJoltage)) {
            return Set.of(fromAdapterArrangement);
        } else {
            return possibleNextJoltAdapters(fromAdapterArrangement).map(fromAdapterArrangement::append)
                    .map(this::buildAllPossibleJoltAdapterArrangements)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }
    }

    private Stream<JoltAdapter> possibleNextJoltAdapters(JoltAdapterArrangement joltAdapterArrangement) {
        JoltAdapter joltAdapter = joltAdapterArrangement.lastJoltAdapter();
        NavigableSet<JoltAdapter> higherRatedJoltAdapters = availableJoltAdapters.tailSet(joltAdapter, false);
        return higherRatedJoltAdapters.stream().takeWhile(joltAdapterArrangement::canAppend);
    }


    public static long tribonacci(int i) {
        if (i == 2) {
            return 1;
        } else if (i > 2) {
            return tribonacci(i - 1) + tribonacci(i - 2) + tribonacci(i - 3);
        } else {
            return 0;
        }
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
