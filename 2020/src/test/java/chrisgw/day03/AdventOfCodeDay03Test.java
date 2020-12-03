package chrisgw.day03;

import chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


public class AdventOfCodeDay03Test {

    private List<String> treeMapExample = List.of( //
            "..##.......", //
            "#...#...#..", //
            ".#....#..#.", //
            "..#.#...#.#", //
            ".#...##..#.", //
            "..#.##.....", //
            ".#.#.#....#", //
            ".#........#", //
            "#.##...#...", //
            "#...##....#", //
            ".#..#...#.#");


    @Test
    public void countTreesAlongSlope_example() {
        TobogganSlope tobogganSlope = new TobogganSlope(3, 1);
        long treesAlongSlope = AdventOfCodeDay03.parseTreeMap(treeMapExample).countTreesAlongSlope(tobogganSlope);
        assertEquals("treesAlongSlope", 7, treesAlongSlope);
    }


    @Test
    public void countTreesAlongSlope_myPuzzleInput() {
        List<String> treeMapExample = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        TobogganSlope tobogganSlope = new TobogganSlope(3, 1);
        long treesAlongSlope = AdventOfCodeDay03.parseTreeMap(treeMapExample).countTreesAlongSlope(tobogganSlope);
        assertEquals("treesAlongSlope", 265, treesAlongSlope);
    }


    // part 02

    @Test
    public void countTreesAlongSlopes_example() {
        Set<TobogganSlope> tobogganSlopes = Set.of( //
                new TobogganSlope(1, 1), //
                new TobogganSlope(3, 1), //
                new TobogganSlope(5, 1), //
                new TobogganSlope(7, 1), //
                new TobogganSlope(1, 2));
        long treesAlongSlopes = AdventOfCodeDay03.parseTreeMap(treeMapExample).countTreesAlongSlopes(tobogganSlopes);
        int expectedTreesAlongSlopes = 2 * 7 * 3 * 4 * 2;
        assertEquals("expectedTreesAlongSlopes", 336, expectedTreesAlongSlopes);
        assertEquals("treesAlongSlopes", expectedTreesAlongSlopes, treesAlongSlopes);
    }


    @Test
    public void countTreesAlongSlopes_myPuzzleInput() {
        Set<TobogganSlope> tobogganSlopes = Set.of( //
                new TobogganSlope(1, 1), //
                new TobogganSlope(3, 1), //
                new TobogganSlope(5, 1), //
                new TobogganSlope(7, 1), //
                new TobogganSlope(1, 2));

        List<String> treeMapExample = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        long treesAlongSlopes = AdventOfCodeDay03.parseTreeMap(treeMapExample).countTreesAlongSlopes(tobogganSlopes);
        int expectedTreesAlongSlopes = 3154761400;
        assertEquals("treesAlongSlopes", expectedTreesAlongSlopes, treesAlongSlopes);
    }

}
