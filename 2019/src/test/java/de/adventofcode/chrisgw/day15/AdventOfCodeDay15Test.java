package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay15Test {

    @Test
    public void myPuzzleInput_part01_shortestDistanceToOxygenSystem() {
        String repairDroidProgramStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay15.txt");
        int expectedDistance = 238;

        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(repairDroidProgramStr);
        int distance = aoc15.shortestDistanceToOxygenSystem();
        System.out.println(aoc15);
        assertEquals("shortestDistanceToOxygenSystem", expectedDistance, distance);
    }

}
