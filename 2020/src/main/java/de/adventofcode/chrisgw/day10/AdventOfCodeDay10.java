package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.AdventOfCodePuzzle;

import java.time.Year;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * https://adventofcode.com/2020/day/10
 */
public class AdventOfCodeDay10 extends AdventOfCodePuzzle {

    private final NavigableSet<JoltAdapter> availableJoltAdapters;
    private final JoltAdapter deviceJoltage;


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
        JoltAdapterArrangement joltAdapterArrangement = new JoltAdapterArrangement();
        for (JoltAdapter nextJoltAdapter : availableJoltAdapters) {
            joltAdapterArrangement = joltAdapterArrangement.append(nextJoltAdapter);
        }
        int joltDifference1 = joltAdapterArrangement.calculateJoltDifferenceSum(1);
        int joltDifference3 = joltAdapterArrangement.calculateJoltDifferenceSum(3);
        return joltDifference1 * joltDifference3;
    }


    @Override
    public Number solveSecondPart() {
        JoltAdapterArrangement startingAdapterArrangement = new JoltAdapterArrangement();
        return buildAllPossibleJoltAdapterArrangements(startingAdapterArrangement).size();
    }


    public Set<JoltAdapterArrangement> buildAllPossibleJoltAdapterArrangements(
            JoltAdapterArrangement currentAdapterArrangement) {
        JoltAdapter lastJoltAdapter = currentAdapterArrangement.lastJoltAdapter();
        if (lastJoltAdapter.equals(deviceJoltage)) {
            return Set.of(currentAdapterArrangement);
        } else {
            return possibleNextJoltAdapters(currentAdapterArrangement).map(currentAdapterArrangement::append)
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


    @Override
    public Year getYear() {
        return Year.of(2020);
    }

    @Override
    public int getDay() {
        return 10;
    }

}
