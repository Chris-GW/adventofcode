package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.day03.Direction;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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


    public long locatedOxygenSystem() {
        List<Direction> someDirections = Stream.generate(Direction::values)
                .flatMap(Arrays::stream)
                .unordered()
                .limit(50000000)
                .collect(Collectors.toList());
        Collections.shuffle(someDirections);
        long foundStatus = someDirections.stream()
                .mapToLong(repairDroid::move)
                .dropWhile(value -> value != RepairDroid.REACHED_LOCATION_STATUS_CODE)
                .findFirst()
                .orElse(0);

        return foundStatus;
    }


    @Override
    public String toString() {
        return repairDroid.toString();
    }

}
