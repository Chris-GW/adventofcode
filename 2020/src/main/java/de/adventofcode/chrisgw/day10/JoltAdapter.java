package de.adventofcode.chrisgw.day10;

import lombok.Data;


@Data
public class JoltAdapter implements Comparable<JoltAdapter> {

    private final int ratedJolts;


    public boolean canAcceptJolts(int jolts) {
        int minJolts = ratedJolts - 3;
        return minJolts <= jolts && jolts <= ratedJolts;
    }

    public boolean canChainWith(JoltAdapter otherJoltAdapter) {
        return canAcceptJolts(otherJoltAdapter.getRatedJolts());
    }


    public int joltsDifferenceTo(JoltAdapter otherJoltAdapter) {
        return this.getRatedJolts() - otherJoltAdapter.getRatedJolts();
    }


    @Override
    public int compareTo(JoltAdapter other) {
        return Integer.compare(this.getRatedJolts(), other.getRatedJolts());
    }

}
