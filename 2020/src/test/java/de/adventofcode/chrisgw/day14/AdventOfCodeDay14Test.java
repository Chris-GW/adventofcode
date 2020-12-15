package de.adventofcode.chrisgw.day14;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay14Test {

    @Test
    public void solveFirstPart_example() {
        List<String> exampleDockingProgramLines = List.of( //
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", //
                "mem[8] = 11", //
                "mem[7] = 101", //
                "mem[8] = 0");
        long expectedTotalMemorySum = 101 + 64;
        assertEquals("expectedTotalMemorySum", 165, expectedTotalMemorySum);

        DockingProgram dockingProgram = new DockingProgram(exampleDockingProgramLines);
        long totalMemorySum = dockingProgram.solveFirstPart();
        assertEquals("totalMemorySum", expectedTotalMemorySum, totalMemorySum);
    }

    @Test
    public void solveFirstPart_myPuzzleInput() {
        long expectedTotalMemorySum = 14839536808842L;
        List<String> dockingProgramLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay14.txt");

        DockingProgram dockingProgram = new DockingProgram(dockingProgramLines);
        long totalMemorySum = dockingProgram.solveFirstPart();
        assertEquals("totalMemorySum", expectedTotalMemorySum, totalMemorySum);
    }


    // part 02

    @Test
    public void solveSecondPart_example() {
        List<String> exampleDockingProgramLines = List.of( //
                "mask = 000000000000000000000000000000X1001X", //
                "mem[42] = 100", //
                "mask = 00000000000000000000000000000000X0XX", //
                "mem[26] = 1");
        long expectedTotalMemorySum = 208;

        DockingProgram dockingProgram = new DockingProgram(exampleDockingProgramLines);
        long totalMemorySum = dockingProgram.solveSecondPart();
        assertEquals("totalMemorySum", expectedTotalMemorySum, totalMemorySum);
    }

    @Test
    public void solveSecondPart_myPuzzleInput() {
        long expectedTotalMemorySum = 4215284199669L;
        List<String> dockingProgramLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay14.txt");

        DockingProgram dockingProgram = new DockingProgram(dockingProgramLines);
        long totalMemorySum = dockingProgram.solveSecondPart();
        assertEquals("totalMemorySum", expectedTotalMemorySum, totalMemorySum);
    }

}
