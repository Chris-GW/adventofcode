package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay07Test {

    public static final Bag SHINY_GOLD = new Bag("shiny gold");
    private List<String> ruleLines = List.of( //
            "light red bags contain 1 bright white bag, 2 muted yellow bags.", //
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.", //
            "bright white bags contain 1 shiny gold bag.", //
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.", //
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.", //
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.", //
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.", //
            "faded blue bags contain no other bags.", //
            "dotted black bags contain no other bags.");


    @Test
    public void countBagsWichCanContain_example() {
        AdventOfCodeDay07 aoc07 = AdventOfCodeDay07.parseBagColorCodeRules(ruleLines);
        long bagColorsCount = aoc07.countBagsWichCanContain(SHINY_GOLD);
        assertEquals("countBagsWichCanContain", 4, bagColorsCount);
    }

    @Test
    public void countBagsWichCanContain_myPuzzleInput() {
        List<String> ruleLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        AdventOfCodeDay07 aoc07 = AdventOfCodeDay07.parseBagColorCodeRules(ruleLines);
        long bagColorsCount = aoc07.countBagsWichCanContain(SHINY_GOLD);
        assertEquals("countBagsWichCanContain", 103, bagColorsCount);
    }


    // part 02

    @Test
    public void countContainedBagsFor_example() {
        AdventOfCodeDay07 aoc07 = AdventOfCodeDay07.parseBagColorCodeRules(ruleLines);
        long bagCount = aoc07.countIndividualBagsInside(SHINY_GOLD);
        int expected = 1 + (1 * 7) + 2 + (2 * 11);
        assertEquals("expectedBagCount", 32, expected);
        assertEquals("countContainedBagsFor", expected, bagCount);
    }

    @Test
    public void countContainedBagsFor_anotherExample() {
        List<String> ruleLines = List.of( //
                "shiny gold bags contain 2 dark red bags.", //
                "dark red bags contain 2 dark orange bags.", //
                "dark orange bags contain 2 dark yellow bags.",  //
                "dark yellow bags contain 2 dark green bags.", //
                "dark green bags contain 2 dark blue bags.", //
                "dark blue bags contain 2 dark violet bags.", //
                "dark violet bags contain no other bags.");
        AdventOfCodeDay07 aoc07 = AdventOfCodeDay07.parseBagColorCodeRules(ruleLines);
        long bagCount = aoc07.countIndividualBagsInside(SHINY_GOLD);
        assertEquals("countContainedBagsFor", 126, bagCount);
    }

    @Test
    public void countContainedBagsFor_myPuzzleInput() {
        List<String> ruleLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay07.txt");
        AdventOfCodeDay07 aoc07 = AdventOfCodeDay07.parseBagColorCodeRules(ruleLines);
        long bagCount = aoc07.countIndividualBagsInside(SHINY_GOLD);
        assertEquals("countContainedBagsFor", 1469, bagCount);
    }

}
