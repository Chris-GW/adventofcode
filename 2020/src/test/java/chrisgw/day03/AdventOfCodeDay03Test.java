package chrisgw.day03;

import chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


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
        long treesAlongSlope = AdventOfCodeDay03.parseTreeMap(treeMapExample).countTreesAlongSlope(3, 1);
        Assert.assertEquals("treesAlongSlope", 7, treesAlongSlope);
    }


    @Test
    public void countTreesAlongSlope_myPuzzleInput() {
        List<String> treeMapExample = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay03.txt");
        long treesAlongSlope = AdventOfCodeDay03.parseTreeMap(treeMapExample).countTreesAlongSlope(3, 1);
        Assert.assertEquals("treesAlongSlope", 265, treesAlongSlope);
    }


}
