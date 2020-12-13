package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day10.AdventOfCodeDay10;
import org.junit.Ignore;
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


    // part 02

    @Test
    public void solveSecondPart_example() {
        long expectedEarliestTimestamp = 1068781;
        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedEarliestTimestamp, solution);
    }

    @Test
    public void solveSecondPart_exampleWiki() {
        long expectedEarliestTimestamp = 39;
        List<String> exampleBusPlanLines = List.of("123", "3,x,x,4,5");
        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedEarliestTimestamp, solution);
    }

    @Test
    public void solveSecondPart_example2() {
        long expectedEarliestTimestamp = 3417;
        List<String> exampleBusPlanLines = List.of("123", "17,x,13,19");
        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedEarliestTimestamp, solution);
    }

    @Test
    public void solveSecondPart_example3() {
        long expectedEarliestTimestamp = 754018;
        List<String> exampleBusPlanLines = List.of("123", "67,7,59,61");
        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedEarliestTimestamp, solution);
    }

    @Test
    public void solveSecondPart_example4() {
        long expectedEarliestTimestamp = 779210;
        List<String> exampleBusPlanLines = List.of("123", "67,x,7,59,61");
        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedEarliestTimestamp, solution);
    }

    @Test
    public void solveSecondPart_example5() {
        long expectedEarliestTimestamp = 1261476;
        List<String> exampleBusPlanLines = List.of("123", "67,7,x,59,61");
        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedEarliestTimestamp, solution);
    }

    @Test
    public void solveSecondPart_example6() {
        long expectedEarliestTimestamp = 1202161486;
        List<String> exampleBusPlanLines = List.of("123", "1789,37,47,1889");
        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(exampleBusPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedEarliestTimestamp, solution);
    }


    @Test
    @Ignore
    public void solveSecondPart_myPuzzleInput() {
        List<String> busPlanLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay13.txt");
        long expectedSolution = 246;

        AdventOfCodeDay13 aoc10 = new AdventOfCodeDay13(busPlanLines);
        long solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedSolution, solution);
    }

}
