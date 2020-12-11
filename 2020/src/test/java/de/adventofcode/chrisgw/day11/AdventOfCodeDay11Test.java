package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay11Test {

    private final List<String> exampleInitialSeatLayout = List.of( //
            "L.LL.LL.LL", //
            "LLLLLLL.LL", //
            "L.L.L..L..", //
            "LLLL.LL.LL", //
            "L.LL.LL.LL", //
            "L.LLLLL.LL", //
            "..L.L.....", //
            "LLLLLLLLLL", //
            "L.LLLLLL.L", //
            "L.LLLLL.LL");

    @Test
    public void solveFirstPart_example() {
        SeatingSystem seatingSystem = new SeatingSystem(exampleInitialSeatLayout);
        long occupiedSeatCount = seatingSystem.solveFirstPart().longValue();
        assertEquals("solveFirstPart occupiedSeatCount", 37L, occupiedSeatCount);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        List<String> initialSeatLayout = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        SeatingSystem seatingSystem = new SeatingSystem(initialSeatLayout);
        long occupiedSeatCount = seatingSystem.solveFirstPart().longValue();
        assertEquals("solveFirstPart occupiedSeatCount", 2238L, occupiedSeatCount);
    }

}
