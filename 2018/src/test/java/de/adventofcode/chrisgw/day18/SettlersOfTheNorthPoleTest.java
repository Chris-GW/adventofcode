package de.adventofcode.chrisgw.day18;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
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
                + ".#.#...|#.\n" // 0
                + ".....#|##|\n" // 1
                + ".|..|...#.\n" // 2
                + "..|#.....#\n" // 3
                + "#.#|||#|#|\n" // 4
                + "...#.||...\n" // 5
                + ".|....|...\n" // 6
                + "||...#|.#|\n" // 7
                + "|.||||..|.\n" // 8
                + "...#.|..|.";  // 9
        SettlersOfTheNorthPole northPole = SettlersOfTheNorthPole.parseNorthPoleAcres(arceAreaLines_example01);
        String initalStateString = northPole.toString();
        System.out.println(initalStateString);
        assertEquals("inital State", expectedInitialStateString, initalStateString);
    }


    @Test
    public void calculateResourceValueAfter_10min_example01() {
        List<String> example01Lines = readAllLinesOfClassPathResource("/day18/example01.txt");
        String[] expectedStates = String.join("\n", example01Lines).split("\n\n");

        int minutes = 10;
        SettlersOfTheNorthPole northPole = SettlersOfTheNorthPole.parseNorthPoleAcres(arceAreaLines_example01);
        for (int i = 1; northPole.getElapsedMinutes() < minutes; i++) {
            northPole = northPole.nextMinute();
            String areaState = northPole.toString();
            System.out.println(areaState);
            assertEquals("area state", expectedStates[i], areaState);
        }

        long expectedResourceValue = 37 * 31;
        long resourceValue = northPole.resourceValue();
        assertEquals("resourceValue", expectedResourceValue, resourceValue);
    }

    @Test
    public void calculateResourceValueAfter_10min_myPuzzleInput() throws Exception {
        int minutes = 10;
        long expectedResourceValue = 518028;

        List<String> arceAreaLines = readAllLinesOfClassPathResource("/day18/myPuzzleInput.txt");
        SettlersOfTheNorthPole northPole = SettlersOfTheNorthPole.parseNorthPoleAcres(arceAreaLines);
        northPole = northPole.toMinute(minutes);
        System.out.println(northPole);

        long resourceValue = northPole.resourceValue();
        assertEquals("resourceValue", expectedResourceValue, resourceValue);
    }

    @Test
    public void calculateResourceValueAfter_1_000_000_000min_myPuzzleInput() throws Exception {
        int minutes = 1_000_000_000;
        long expectedResourceValue = 518028;

        List<String> arceAreaLines = readAllLinesOfClassPathResource("/day18/myPuzzleInput.txt");
        SettlersOfTheNorthPole northPole = SettlersOfTheNorthPole.parseNorthPoleAcres(arceAreaLines);
        northPole = northPole.toMinute(minutes);

        long resourceValue = northPole.resourceValue();
        assertEquals("resourceValue", expectedResourceValue, resourceValue);
    }

}