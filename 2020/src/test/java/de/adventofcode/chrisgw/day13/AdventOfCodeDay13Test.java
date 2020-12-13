package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day10.AdventOfCodeDay10;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay13Test {


    private final List<String> exampleBusPlanLines = List.of("939", "7,13,x,x,59,x,31,19");


    @Test
    public void solveFirstPart_example() {
        int expectedWaitTime = 944 - 939;
        assertEquals("expectedWaitTime", 5, expectedWaitTime);
        int expectedBusId = 59;
        int expectedSolution = expectedBusId * expectedWaitTime;
        assertEquals("expectedSolution", 295, expectedSolution);

        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        int solution = aoc10.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        List<String> busPlanLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay13.txt");
        int expectedSolution = 246;

        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(busPlanLines);
        int solution = aoc10.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

}
