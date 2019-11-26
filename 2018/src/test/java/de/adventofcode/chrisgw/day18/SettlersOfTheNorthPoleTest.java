package de.adventofcode.chrisgw.day18;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class SettlersOfTheNorthPoleTest {

    private List<String> arceAreaLines_example01 = Arrays.asList( //
            ".#.#...|#.", // 0
            ".....#|##|", // 1
            ".|..|...#.", // 2
            "..|#.....#", // 3
            "#.#|||#|#|", // 4
            "...#.||...", // 5
            ".|....|...", // 6
            "||...#|.#|", // 7
            "|.||||..|.", // 8
            "...#.|..|.");// 9


    @Test
    public void parseClayVeinLine_example_01() {
        String expectedInitialStateString = "" //
                + "Initial state:\n" //
                + ".#.#...|#.\n" //
                + ".....#|##|\n" //
                + ".|..|...#.\n" //
                + "..|#.....#\n" //
                + "#.#|||#|#|\n" //
                + "...#.||...\n" //
                + ".|....|...\n" //
                + "||...#|.#|\n" //
                + "|.||||..|.\n" //
                + "...#.|..|.";

        SettlersOfTheNorthPole northPole = SettlersOfTheNorthPole.parseNorthPoleAcres(arceAreaLines_example01);
        String initalStateString = northPole.toString();
        System.out.println(initalStateString);
        assertEquals("inital State", expectedInitialStateString, initalStateString);
    }


    @Test
    public void calculateResourceValueAfter_10min_example01() {
        int minutes = 10;
        long expectedResourceValue = 37 * 31;
        SettlersOfTheNorthPole northPole = SettlersOfTheNorthPole.parseNorthPoleAcres(arceAreaLines_example01);
        while (northPole.getElapsedMinutes() < minutes) {
            northPole = northPole.nextMinute();
            System.out.println(northPole);
        }
        long resourceValue = northPole.resourceValue();
        assertEquals("resourceValue", expectedResourceValue, resourceValue);
    }

    @Test
    public void calculateResourceValueAfter_10min_myPuzzleInput() throws Exception {
        int minutes = 10;
        long expectedResourceValue = 37 * 31;

        List<String> arceAreaLines = TestUtils.readAllLinesOfClassPathResource("/day18/myPuzzleInput.txt");
        SettlersOfTheNorthPole northPole = SettlersOfTheNorthPole.parseNorthPoleAcres(arceAreaLines);
        while (northPole.getElapsedMinutes() < minutes) {
            northPole = northPole.nextMinute();
            System.out.println(northPole);
        }
        long resourceValue = northPole.resourceValue();
        assertEquals("resourceValue", expectedResourceValue, resourceValue);
    }

}