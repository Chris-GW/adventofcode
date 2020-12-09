package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class AdventOfCodeDay09Test {

    private List<Integer> exampleNumbers = List.of( //
            35, //
            20, //
            15, //
            25, //
            47, //
            40, //
            62, //
            55, //
            65, //
            95, //
            102, //
            117, //
            150, //
            182, //
            127, //
            219, //
            299, //
            277, //
            309, //
            576);

    @Test
    public void findFirstNumberWhichDoesntMatchPrembleSumPair_example() {
        int expectedInvalidNumber = 127;
        int preambleLength = 5;
        AdventOfCodeDay09 aoc09 = new AdventOfCodeDay09(preambleLength);
        for (int number : exampleNumbers) {
            boolean accepted = aoc09.readNext(number);
            if (!accepted) {
                assertEquals("first invalid number", expectedInvalidNumber, number);
                return;
            }
        }
        fail("no invalid number found");
    }

    @Test
    public void findFirstNumberWhichDoesntMatchPrembleSumPair_myPuzzleInput() {
        int expectedInvalidNumber = 542529149;
        int preambleLength = 25;
        Iterator<Integer> inputNumbers = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay09.txt")
                .stream()
                .map(Integer::parseInt)
                .iterator();
        AdventOfCodeDay09 aoc09 = new AdventOfCodeDay09(preambleLength);
        while (inputNumbers.hasNext()) {
            int number = inputNumbers.next();
            boolean accepted = aoc09.readNext(number);
            if (!accepted) {
                assertEquals("first invalid number", expectedInvalidNumber, number);
                return;
            }
        }
        fail("no invalid number found");
    }


    // part 02

    @Test
    public void findContigousListForInvalidNumber_example() {
        int expectedInvalidNumber = 127;
        int expectedMinMaxSum = 15 + 47;
        assertEquals("expectedMinMaxSum", 62, expectedMinMaxSum);

        int preambleLength = 5;
        AdventOfCodeDay09 aoc09 = new AdventOfCodeDay09(preambleLength);
        for (int number : exampleNumbers) {
            boolean accepted = aoc09.readNext(number);
            if (!accepted) {
                assertEquals("first invalid number", expectedInvalidNumber, number);
                int minMaxSum = aoc09.findContigousListForInvalidNumber(number);
                assertEquals("findContigousListForInvalidNumber", expectedMinMaxSum, minMaxSum);
                return;
            }
        }
        fail("no invalid number found");
    }

    @Test
    public void findContigousListForInvalidNumber_myPuzzleInput() {
        int expectedInvalidNumber = 542529149;
        int expectedMinMaxSum = 75678618;
        int preambleLength = 25;
        Iterator<Integer> inputNumbers = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay09.txt")
                .stream()
                .map(Integer::parseInt)
                .iterator();
        AdventOfCodeDay09 aoc09 = new AdventOfCodeDay09(preambleLength);
        while (inputNumbers.hasNext()) {
            int number = inputNumbers.next();
            boolean accepted = aoc09.readNext(number);
            if (!accepted) {
                assertEquals("first invalid number", expectedInvalidNumber, number);
                int minMaxSum = aoc09.findContigousListForInvalidNumber(number);
                assertEquals("findContigousListForInvalidNumber", expectedMinMaxSum, minMaxSum);
                return;
            }
        }
        fail("no invalid number found");
    }

}
