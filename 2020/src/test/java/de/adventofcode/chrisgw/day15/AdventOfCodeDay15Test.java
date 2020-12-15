package de.adventofcode.chrisgw.day15;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay15Test {


    @Test
    public void solveFirstPart_example01() {
        List<Integer> startingNumbers = List.of(0, 3, 6);
        int expectedSolution = 436;

        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_example02() {
        List<Integer> startingNumbers = List.of(1, 3, 2);
        int expectedSolution = 1;

        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_example03() {
        List<Integer> startingNumbers = List.of(2, 1, 3);
        int expectedSolution = 10;

        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_example04() {
        List<Integer> startingNumbers = List.of(2, 3, 1);
        int expectedSolution = 78;

        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_example05() {
        List<Integer> startingNumbers = List.of(3, 2, 1);
        int expectedSolution = 438;

        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_example06() {
        List<Integer> startingNumbers = List.of(3, 1, 2);
        int expectedSolution = 1836;

        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }


    @Test
    public void solveFirstPart_myPuzzleInput() {
        int expectedSolution = 1194;

        List<Integer> startingNumbers = List.of(6, 13, 1, 15, 2, 0);
        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }


    // part 02

    @Test
    public void solveSecondPart_example01() {
        int expectedSolution = 175594;

        List<Integer> startingNumbers = List.of(0, 3, 6);
        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveSecondPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveSecondPart_myPuzzleInput() {
        int expectedSolution = 48710;

        List<Integer> startingNumbers = List.of(6,13,1,15,2,0);
        AdventOfCodeDay15 aoc15 = new AdventOfCodeDay15(startingNumbers);
        int solution = aoc15.solveSecondPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

}
