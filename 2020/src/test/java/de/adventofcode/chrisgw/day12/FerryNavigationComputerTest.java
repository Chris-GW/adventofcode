package de.adventofcode.chrisgw.day12;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class FerryNavigationComputerTest {

    private final List<String> navigationInstructions = List.of( //
            "F10", //
            "N3", //
            "F7", //
            "R90", //
            "F11");


    @Test
    public void solveFirstPart_example() {
        int expectedSolution = 17 + 8;
        assertEquals("expectedSolution", 25, expectedSolution);

        FerryNavigationComputer navigationComputer = new FerryNavigationComputer(navigationInstructions);
        int solution = navigationComputer.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        int expectedSolution = 364;
        List<String> navigationInstructions = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay12.txt");

        FerryNavigationComputer navigationComputer = new FerryNavigationComputer(navigationInstructions);
        int solution = navigationComputer.solveFirstPart();
        assertEquals("solveFirstPart", expectedSolution, solution);
    }


    // part 02

    @Test
    public void solveSecondPart_example() {
        int expectedSolution = 214 + 72;
        assertEquals("expectedSolution", 286, expectedSolution);

        FerryNavigationComputer navigationComputer = new FerryNavigationComputer(navigationInstructions);
        int solution = navigationComputer.solveSecondPart();
        assertEquals("solveSecondPart", expectedSolution, solution);
    }

    @Test
    public void solveSecondPart_myPuzzleInput() {
        int expectedSolution = 39518;
        List<String> navigationInstructions = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay12.txt");

        FerryNavigationComputer navigationComputer = new FerryNavigationComputer(navigationInstructions);
        int solution = navigationComputer.solveSecondPart();
        assertEquals("solveSecondPart", expectedSolution, solution);
    }

}
