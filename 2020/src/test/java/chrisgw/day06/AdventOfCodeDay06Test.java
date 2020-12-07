package chrisgw.day06;

import chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import static org.junit.Assert.*;


public class AdventOfCodeDay06Test {

    @Test
    public void countPersonGroupAffirmedQuestions_example() {
        StringReader personGroupFormsReader = new StringReader("" //
                + "abc\n" + "\n"  //
                + "a\n" + "b\n" + "c\n" + "\n" //
                + "ab\n" + "ac\n" + "\n"  //
                + "a\n" + "a\n" + "a\n" + "a\n" + "\n" //
                + "b");
        AdventOfCodeDay06 aoc06 = AdventOfCodeDay06.parsePersonGroupCustomsDeclarationForms(personGroupFormsReader);
        long affirmedQuestions = aoc06.countPersonGroupAffirmedQuestions();
        int expectedAffirmedQuestions = 3 + 3 + 3 + 1 + 1;
        assertEquals("expectedaffirmedQuestions", 11, expectedAffirmedQuestions);
        assertEquals("affirmedQuestions", expectedAffirmedQuestions, affirmedQuestions);
    }

    @Test
    public void countPersonGroupAffirmedQuestions_myPuzzleInput() {
        InputStream personGroupFormsInput = TestUtils.class.getResourceAsStream("/puzzleInputDay06.txt");
        Readable personGroupFormsReader = new InputStreamReader(personGroupFormsInput);
        AdventOfCodeDay06 aoc06 = AdventOfCodeDay06.parsePersonGroupCustomsDeclarationForms(personGroupFormsReader);
        long affirmedQuestions = aoc06.countPersonGroupAffirmedQuestions();
        assertEquals("affirmedQuestions", 6930, affirmedQuestions);
    }

}
