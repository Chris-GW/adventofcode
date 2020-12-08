package de.adventofcode.chrisgw.day02;

import org.junit.Test;

import java.util.List;

import static de.adventofcode.chrisgw.TestUtils.readAllLinesOfClassPathResource;
import static de.adventofcode.chrisgw.day02.AdventOfCodeDay02.countValidPasswordsWithSledRentalPlacePasswordPolicy;
import static de.adventofcode.chrisgw.day02.AdventOfCodeDay02.countValidPasswordsWithTobogganCorporatePasswordPolicy;
import static org.junit.Assert.*;


public class AdventOfCodeDay02Test {

    private final List<String> examplePasswordList = List.of( //
            "1-3 a: abcde", //
            "1-3 b: cdefg", //
            "2-9 c: ccccccccc");


    @Test
    public void countValidPasswordsWithSledRentalPlacePasswordPolicy_example() {
        long validPasswords = countValidPasswordsWithSledRentalPlacePasswordPolicy(examplePasswordList);
        assertEquals("countValidPasswords", 2, validPasswords);
    }


    @Test
    public void countValidPasswordsWithSledRentalPlacePasswordPolicy_myPuzzleInput() {
        List<String> passwordList = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        long validPasswords = countValidPasswordsWithSledRentalPlacePasswordPolicy(passwordList);
        assertEquals("countValidPasswords", 447, validPasswords);
    }


    // part 02

    @Test
    public void countValidPasswordsWithTobogganCorporatePasswordPolicy_example() {
        long validPasswords = countValidPasswordsWithTobogganCorporatePasswordPolicy(examplePasswordList);
        assertEquals("countValidPasswords", 1, validPasswords);
    }


    @Test
    public void countValidPasswordsWithTobogganCorporatePasswordPolicy_myPuzzleInput() {
        List<String> passwordList = readAllLinesOfClassPathResource("/puzzleInputDay02.txt");
        long validPasswords = countValidPasswordsWithTobogganCorporatePasswordPolicy(passwordList);
        assertEquals("countValidPasswords", 249, validPasswords);
    }


}
