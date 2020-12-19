package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.day15.AdventOfCodeDay15;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay17Test {


    @Test
    public void solveFirstPart_example() {
        List<String> startingNumbers = List.of("");
        int expectedSolution = 436;

        AdventOfCodeDay17 aoc = new AdventOfCodeDay17(startingNumbers);
        int solution = aoc.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        int expectedSolution = 1194;

        List<String> startingNumbers = List.of("");
        AdventOfCodeDay17 aoc = new AdventOfCodeDay17(startingNumbers);
        int solution = aoc.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }


    // part 02

    @Test
    public void solveSecondPart_example() {
        int expectedSolution = 175594;

        List<String> startingNumbers = List.of("");
        AdventOfCodeDay17 aoc = new AdventOfCodeDay17(startingNumbers);
        int solution = aoc.solveSecondPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveSecondPart_myPuzzleInput() {
        int expectedSolution = 48710;

        List<String> startingNumbers = List.of("");
        AdventOfCodeDay17 aoc = new AdventOfCodeDay17(startingNumbers);
        int solution = aoc.solveSecondPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

}
