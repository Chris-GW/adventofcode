package de.adventofcode.chrisgw.day11;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class SeatingSystemTest {

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
        long expectedOccupiedSeatCount = 37;
        SeatingSystem seatingSystem = new SeatingSystem(exampleInitialSeatLayout);
        long occupiedSeatCount = seatingSystem.solveFirstPart();
        assertEquals("solveFirstPart occupiedSeatCount", expectedOccupiedSeatCount, occupiedSeatCount);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        long expectedOccupiedSeatCount = 2238;
        List<String> initialSeatLayout = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        SeatingSystem seatingSystem = new SeatingSystem(initialSeatLayout);
        long occupiedSeatCount = seatingSystem.solveFirstPart();
        assertEquals("solveFirstPart occupiedSeatCount", expectedOccupiedSeatCount, occupiedSeatCount);
    }


    // part 02

    @Test
    public void solveSecondPart_example() {
        long expectedOccupiedSeatCount = 26;
        SeatingSystem seatingSystem = new SeatingSystem(exampleInitialSeatLayout);
        long occupiedSeatCount = seatingSystem.solveSecondPart();
        assertEquals("solveSecondPart occupiedSeatCount", expectedOccupiedSeatCount, occupiedSeatCount);
    }

    @Test
    public void solveSecondPart_myPuzzleInput() {
        long expectedOccupiedSeatCount = 2013;
        List<String> initialSeatLayout = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay11.txt");
        SeatingSystem seatingSystem = new SeatingSystem(initialSeatLayout);
        long occupiedSeatCount = seatingSystem.solveSecondPart();
        assertEquals("solveSecondPart occupiedSeatCount", expectedOccupiedSeatCount, occupiedSeatCount);
    }

}
