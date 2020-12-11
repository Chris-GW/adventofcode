package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day10.AdventOfCodeDay10;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay11Test {


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
        List<String> joltAdapterRartingList = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        AdventOfCodeDay11 aoc11 = new AdventOfCodeDay11(joltAdapterRartingList);
        Number solution = aoc11.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

}
