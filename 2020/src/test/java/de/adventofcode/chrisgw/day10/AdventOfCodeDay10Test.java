package de.adventofcode.chrisgw.day10;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay10Test {


    @Test
    public void solveFirstPart_example() {
        int expectedSolution = 7 * 5;
        List<String> joltAdapterRartingList = List.of("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4");
        AdventOfCodeDay10 aoc10 = new AdventOfCodeDay10(joltAdapterRartingList);
        Number solution = aoc10.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_otherLargerExample() {
        int expectedSolution = 22 * 10;
        List<String> joltAdapterRartingList = List.of("28", "33", "18", "42", "31", "14", "46", "20", "48", "47", "24",
                "23", "49", "45", "19", "38", "39", "11", "1", "32", "25", "35", "8", "17", "7", "9", "4", "2", "34",
                "10", "3");
        AdventOfCodeDay10 aoc10 = new AdventOfCodeDay10(joltAdapterRartingList);
        Number solution = aoc10.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        int expectedSolution = 1890;
        List<String> joltAdapterRartingList = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        AdventOfCodeDay10 aoc10 = new AdventOfCodeDay10(joltAdapterRartingList);
        Number solution = aoc10.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }


    // part 02


    @Test
    public void solveSecondPart_example() {
        long expectedSolution = 8;
        List<String> joltAdapterRartingList = List.of("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4");
        AdventOfCodeDay10 aoc10 = new AdventOfCodeDay10(joltAdapterRartingList);
        Number solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedSolution, solution);
    }

    @Test
    public void solveSecondPart_otherLargerExample() {
        long expectedSolution = 19208;
        List<String> joltAdapterRartingList = List.of("28", "33", "18", "42", "31", "14", "46", "20", "48", "47", "24",
                "23", "49", "45", "19", "38", "39", "11", "1", "32", "25", "35", "8", "17", "7", "9", "4", "2", "34",
                "10", "3");
        AdventOfCodeDay10 aoc10 = new AdventOfCodeDay10(joltAdapterRartingList);
        Number solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedSolution, solution);
    }


    @Test
    public void solveSecondPart_myPuzzleInput() {
        long expectedSolution = 1890;
        List<String> joltAdapterRartingList = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        AdventOfCodeDay10 aoc10 = new AdventOfCodeDay10(joltAdapterRartingList);
        Number solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedSolution, solution);
    }


    @Test
    public void tribonacciTest() {
        List<Integer> firstFewTribonacciNumbers = List.of(0, 0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274, 504, 927, 1705,
                3136, 5768, 10609, 19513, 35890, 66012);
        for (int i = 0; i < firstFewTribonacciNumbers.size(); i++) {
            long value = AdventOfCodeDay10.tribonacci(i);
            long expectedValue = firstFewTribonacciNumbers.get(i);
            assertEquals("tribonacci(" + i + ")", expectedValue, value);
        }
    }

}
