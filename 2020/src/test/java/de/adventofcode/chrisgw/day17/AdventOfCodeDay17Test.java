package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay17Test {


    @Test
    public void solveFirstPart_example() {
        List<String> startingCubeGrid = List.of( //
                ".#.", //
                "..#", //
                "###");
        long expectedSolution = 112;

        PocketDimension aoc = new PocketDimension(startingCubeGrid);
        long solution = aoc.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        long expectedSolution = 319;

        List<String> startingCubeGrid = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay17.txt");
        PocketDimension aoc = new PocketDimension(startingCubeGrid);
        long solution = aoc.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }


    // part 02

    @Test
    public void solveSecondPart_example() {
        List<String> startingCubeGrid = List.of( //
                ".#.", //
                "..#", //
                "###");
        long expectedSolution = 848;

        PocketDimension aoc = new PocketDimension(startingCubeGrid);
        long solution = aoc.solveSecondPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveSecondPart_myPuzzleInput() {
        long expectedSolution = 2324;

        List<String> startingCubeGrid = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay17.txt");
        PocketDimension aoc = new PocketDimension(startingCubeGrid);
        long solution = aoc.solveSecondPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

}
