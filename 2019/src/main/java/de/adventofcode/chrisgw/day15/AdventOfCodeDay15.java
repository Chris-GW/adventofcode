package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.intcode.IntCodeProgram;


/**
 * 2019 Day 15: Oxygen System
 * https://adventofcode.com/2019/day/15
 */
public class AdventOfCodeDay15 {

    private RepairDroid repairDroid;


    public AdventOfCodeDay15(String repairDroidProgramStr) {
        IntCodeProgram droidProgram = IntCodeProgram.parseIntCodeProgram(repairDroidProgramStr);
        this.repairDroid = new RepairDroid(droidProgram);
    }


    public int shortestDistanceToOxygenSystem() {
        return repairDroid.shortestDistanceToOxygenSystem();
    }

    public int neededTimeForOxygenToSpread() {
        return repairDroid.neededTimeForOxygenToSpread();
    }


    @Override
    public String toString() {
        return repairDroid.toString();
    }

}
