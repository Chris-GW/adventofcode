package chrisgw.day02;

import chrisgw.TestUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class AdventOfCodeDay02Test {

    @Test
    public void countValidPasswords_example() {
        List<String> passwordList = List.of( //
                "1-3 a: abcde", //
                "1-3 b: cdefg", //
                "2-9 c: ccccccccc");
        long validPasswords = AdventOfCodeDay02.countValidPasswords(passwordList);
        assertEquals("countValidPasswords", 2, validPasswords);
    }


    @Test
    public void countValidPasswords_myPuzzleInput() {
        List<String> passwordList = TestUtils.readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        long validPasswords = AdventOfCodeDay02.countValidPasswords(passwordList);
        assertEquals("countValidPasswords", 447, validPasswords);
    }

}
