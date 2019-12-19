package de.adventofcode.chrisgw.day15;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay15Test {

    @Test
    public void myPuzzleInput_part01_locate() {
        String repairDroidProgramStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay15.txt");
        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(repairDroidProgramStr);
        aoc15.locatedOxygenSystem();
        System.out.println(aoc15);
        assertNotNull(aoc15);
    }

}
