package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day10.AdventOfCodeDay10;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay12Test {


    @Test
    public void solveFirstPart_example() {
        int expectedSolution = 7 * 5;
        List<String> joltAdapterRartingList = List.of("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4");
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
    public void solveSecondPart_myPuzzleInput() {
        long expectedSolution = 49607173328384L;
        List<String> joltAdapterRartingList = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay10.txt");
        AdventOfCodeDay10 aoc10 = new AdventOfCodeDay10(joltAdapterRartingList);
        Number solution = aoc10.solveSecondPart();
        assertEquals("solveSecondPart", expectedSolution, solution);
    }

}
