package de.adventofcode.chrisgw.day10;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JoltAdapterArrangement implements Iterable<JoltAdapter> {

    private final Deque<JoltAdapter> joltAdapters = new ArrayDeque<>();


    public JoltAdapterArrangement() {
        JoltAdapter outputJolts = new JoltAdapter(0);
        joltAdapters.add(outputJolts);
    }

    public JoltAdapterArrangement(JoltAdapterArrangement otherJoltAdapterArrangement) {
        joltAdapters.addAll(otherJoltAdapterArrangement.joltAdapters);
    }


    public int calculateJoltDifferenceSum(int joltDifference) {
        int sum = 0;
        JoltAdapter previousJoltAdapter = joltAdapters.getFirst();
        for (JoltAdapter nextJoltAdapter : joltAdapters) {
            int actualDifference = nextJoltAdapter.getRatedJolts() - previousJoltAdapter.getRatedJolts();
            if (actualDifference == joltDifference) {
                sum++;
            }
            previousJoltAdapter = nextJoltAdapter;
        }
        return sum;
    }


    public JoltAdapterArrangement append(JoltAdapter nextJoltAdapter) {
        if (!canAppend(nextJoltAdapter)) {
            throw new IllegalArgumentException("can't append JoltAdapter " + nextJoltAdapter);
        }
        JoltAdapterArrangement joltAdapterArrangement = new JoltAdapterArrangement(this);
        joltAdapterArrangement.joltAdapters.add(nextJoltAdapter);
        return joltAdapterArrangement;
    }

    public boolean canAppend(JoltAdapter nextJoltAdapter) {
        return lastJoltAdapter().canAppend(nextJoltAdapter);
    }


    public boolean contains(JoltAdapter joltAdapter) {
        return joltAdapters.contains(joltAdapter);
    }


    public Stream<JoltAdapter> joltAdaters() {
        return joltAdapters.stream();
    }

    public JoltAdapter lastJoltAdapter() {
        return joltAdapters.getLast();
    }


    @Override
    public Iterator<JoltAdapter> iterator() {
        return joltAdapters.iterator();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof JoltAdapterArrangement))
            return false;

        JoltAdapterArrangement that = (JoltAdapterArrangement) o;
        return joltAdapters.equals(that.joltAdapters);
    }

    @Override
    public int hashCode() {
        return joltAdapters.hashCode();
    }

    public String toString() {
        return joltAdaters().map(JoltAdapter::toString).collect(Collectors.joining(", ", "(", ")"));
    }

}
