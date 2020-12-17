package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day15.AdventOfCodeDay15;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay16Test {


    @Test
    public void solveFirstPart_example01() {
        List<String> exampleInputLines = List.of( //
                "class: 1-3 or 5-7", //
                "row: 6-11 or 33-44", //
                "seat: 13-40 or 45-50", //
                "", //
                "your ticket:", //
                "7,1,14", //
                "", //
                "nearby tickets:", //
                "7,3,47", //
                "40,4,50", //
                "55,2,20", //
                "38,6,12");
        int expectedSolution = 4 + 55 + 12;
        assertEquals("expectedSolution", 71, expectedSolution);

        AdventOfCodeDay16 aoc16 = new AdventOfCodeDay16(exampleInputLines);
        int solution = aoc16.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        int expectedSolution = 20231;

        List<String> inputLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay16.txt");
        AdventOfCodeDay16 aoc16 = new AdventOfCodeDay16(inputLines);
        int solution = aoc16.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

}
