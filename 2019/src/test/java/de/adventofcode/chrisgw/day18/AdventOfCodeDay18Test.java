package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay18Test {

    @Test
    public void example01_part01_shortestPath() {
        List<String> tunnelMap = List.of( //
                "#########", //
                "#b.A.@.a#", //
                "#########");
        int expectedSteps = 8;

        AdventOfCodeDay18 aocDay18 = new AdventOfCodeDay18(tunnelMap);
        AdventOfCodeDay18 shortestPath = aocDay18.findShortestPath();
        assertEquals("steps", expectedSteps, shortestPath.getSteps());
    }

    @Test
    public void example02_part01_shortestPath() {
        List<String> tunnelMap = List.of( //
                "########################", //
                "#f.D.E.e.C.b.A.@.a.B.c.#", //
                "######################.#", //
                "#d.....................#", //
                "########################");
        int expectedSteps = 86;

        AdventOfCodeDay18 aocDay18 = new AdventOfCodeDay18(tunnelMap);
        AdventOfCodeDay18 shortestPath = aocDay18.findShortestPath();
        assertEquals("steps", expectedSteps, shortestPath.getSteps());
    }

    @Test
    public void example03_part01_shortestPath() {
        List<String> tunnelMap = List.of( //
                "########################", //
                "#...............b.C.D.f#", //
                "#.######################", //
                "#.....@.a.B.c.d.A.e.F.g#", //
                "########################");
        int expectedSteps = 132;

        AdventOfCodeDay18 aocDay18 = new AdventOfCodeDay18(tunnelMap);
        AdventOfCodeDay18 shortestPath = aocDay18.findShortestPath();
        assertEquals("steps", expectedSteps, shortestPath.getSteps());
    }

    @Test
    @Ignore
    public void example04_part01_shortestPath() {
        List<String> tunnelMap = List.of( //
                "#################", //
                "#i.G..c...e..H.p#", //
                "########.########", //
                "#j.A..b...f..D.o#", //
                "########@########", //
                "#k.E..a...g..B.n#", //
                "########.########", //
                "#l.F..d...h..C.m#", //
                "#################");
        int expectedSteps = 136;

        AdventOfCodeDay18 aocDay18 = new AdventOfCodeDay18(tunnelMap);
        AdventOfCodeDay18 shortestPath = aocDay18.findShortestPath();
        assertEquals("steps", expectedSteps, shortestPath.getSteps());
    }

    @Test
    public void example05_part01_shortestPath() {
        List<String> tunnelMap = List.of( //
                "########################", //
                "#@..............ac.GI.b#", //
                "###d#e#f################", //
                "###A#B#C################", //
                "###g#h#i################", //
                "########################");
        int expectedSteps = 81;

        AdventOfCodeDay18 aocDay18 = new AdventOfCodeDay18(tunnelMap);
        AdventOfCodeDay18 shortestPath = aocDay18.findShortestPath();
        assertEquals("steps", expectedSteps, shortestPath.getSteps());
    }


    @Test
    public void myPuzzleInput_part01_shortestPath() {
        List<String> tunnelMap = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay18.txt");
        int expectedSteps = 4824; // too high

        AdventOfCodeDay18 aocDay18 = new AdventOfCodeDay18(tunnelMap);
        AdventOfCodeDay18 shortestPath = aocDay18.findShortestPath();
        assertEquals("steps", expectedSteps, shortestPath.getSteps());
    }

}
