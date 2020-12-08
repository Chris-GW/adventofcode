package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay05Test {


    @Test
    public void parseSeatForBoardingPass_example() {
        String seatCode = "FBFBBFFRLR";
        BoardingPass boardingPass = new BoardingPass(seatCode);
        assertEquals("row", 44, boardingPass.getRow());
        assertEquals("column", 5, boardingPass.getColumn());
        int expectedId = 44 * 8 + 5;
        assertEquals("expected id", 357, expectedId);
        assertEquals("id", expectedId, boardingPass.getId());
    }

    @Test
    public void findMaxBoardingPassId_myPuzzleInput() {
        List<String> scannedBoardingPasses = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay05.txt");
        BoardingPass maxBoardingPassId = AdventOfCodeDay05.findMaxBoardingPassId(scannedBoardingPasses);
        assertEquals("id", 911, maxBoardingPassId.getId());
    }


    // part 02
    @Test
    public void findFreeSeat_myPuzzleInput() {
        List<String> scannedBoardingPasses = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay05.txt");
        BoardingPass freeSeat = AdventOfCodeDay05.findFreeSeat(scannedBoardingPasses);
        assertEquals("id", 629, freeSeat.getId());
    }

}
