package chrisgw.day08;

import chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay08Test {

    private List<String> exampleBootProgram = List.of( //
            "nop +0", //
            "acc +1", //
            "jmp +4", //
            "acc +3", //
            "jmp -3", //
            "acc -99", //
            "acc +1", //
            "jmp -4", //
            "acc +6");


    @Test
    public void findBootProgramInfiniteLoop_example() {
        AdventOfCodeDay08 aoc08 = AdventOfCodeDay08.parseHandHeldGameConsoleBootProgram(exampleBootProgram);
        int accumulator = aoc08.findBootProgramInfiniteLoop();
        assertEquals("accumulator", 5, accumulator);
    }

    @Test
    public void findBootProgramInfiniteLoop_myPuzzleInput() {
        List<String> bootProgramLines = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay08.txt");
        AdventOfCodeDay08 aoc08 = AdventOfCodeDay08.parseHandHeldGameConsoleBootProgram(bootProgramLines);
        int accumulator = aoc08.findBootProgramInfiniteLoop();
        assertEquals("accumulator", 2025, accumulator);
    }

}
