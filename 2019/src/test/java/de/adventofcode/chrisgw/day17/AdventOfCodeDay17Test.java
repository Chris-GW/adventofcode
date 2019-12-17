package de.adventofcode.chrisgw.day17;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.intcode.IntCodeProgram;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay17Test {

    @Test
    public void example01_part01_sumOfCameraAlignmentParameters() {
        String cameraImageStr = ""  //
                + "..#..........\n" //
                + "..#..........\n" //
                + "#######...###\n" //
                + "#.#...#...#.#\n" //
                + "#############\n" //
                + "..#...#...#..\n" //
                + "..#####...^..";
        IntCodeProgram asciiProgram = IntCodeProgram.parseIntCodeProgram(
                TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay17.txt"));
        long expectedSumOfCameraAlignmentParameters = (2 * 2) + (2 * 4) + 24 + 40; // 76

        AdventOfCodeDay17 aoc17 = new AdventOfCodeDay17(asciiProgram);
        aoc17.withCameraImage(cameraImageStr);
        System.out.println(aoc17);
        long sumOfCameraAlignmentParameters = aoc17.sumOfCameraAlignmentParameters();

        assertEquals("sumOfCameraAlignmentParameters", expectedSumOfCameraAlignmentParameters,
                sumOfCameraAlignmentParameters);
    }


    @Test
    public void myPuzzleInput_part01_sumOfCameraAlignmentParameters() {
        IntCodeProgram asciiProgram = IntCodeProgram.parseIntCodeProgram(
                TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay17.txt"));
        long expectedSumOfCameraAlignmentParameters = 5056;

        AdventOfCodeDay17 aoc17 = new AdventOfCodeDay17(asciiProgram);
        aoc17.run();
        System.out.println(aoc17);
        long sumOfCameraAlignmentParameters = aoc17.sumOfCameraAlignmentParameters();

        assertEquals("sumOfCameraAlignmentParameters", expectedSumOfCameraAlignmentParameters,
                sumOfCameraAlignmentParameters);
    }

}