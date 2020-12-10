package de.adventofcode.chrisgw.day10;

import lombok.Data;


@Data
public class JoltAdapter implements Comparable<JoltAdapter> {

    private final int ratedJolts;


    public boolean canAcceptInputJolts(int inputJolts) {
        int minJolts = ratedJolts - 3;
        return minJolts <= inputJolts && inputJolts <= ratedJolts;
    }

    public boolean canAppend(JoltAdapter otherJoltAdapter) {
        return otherJoltAdapter.canAcceptInputJolts(this.getRatedJolts());
    }


    public int joltsDifferenceTo(JoltAdapter otherJoltAdapter) {
        return this.getRatedJolts() - otherJoltAdapter.getRatedJolts();
    }


    @Override
    public int compareTo(JoltAdapter other) {
        return Integer.compare(this.getRatedJolts(), other.getRatedJolts());
    }

    @Override
    public String toString() {
        return String.valueOf(ratedJolts);
    }

}
