package chrisgw.day04;

import chrisgw.TestUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.*;


public class AdventOfCodeDay04Test {


    @Test
    public void countValidPassportsFromBatch_example() {
        StringReader passportBatch = new StringReader(""  //
                + "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" //
                + "byr:1937 iyr:2017 cid:147 hgt:183cm\n" //
                + "\n" //
                + "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" //
                + "hcl:#cfa07d byr:1929\n" //
                + "\n" //
                + "hcl:#ae17e1 iyr:2013\n" //
                + "eyr:2024\n" //
                + "ecl:brn pid:760753108 byr:1931\n" //
                + "hgt:179cm\n" //
                + "\n" //
                + "hcl:#cfa07d eyr:2025 pid:166559648\n" //
                + "iyr:2011 ecl:brn hgt:59in");
        long validPassports = AdventOfCodeDay04.countValidPassports(passportBatch);
        assertEquals("validPassports", 2, validPassports);
    }


    @Test
    public void countValidPassportsFromBatch_myPuzzleInpit() {
        InputStream passportBatchInputStream = TestUtils.class.getResourceAsStream("/puzzleInputDay04.txt");
        Readable passportBatchReader = new InputStreamReader(passportBatchInputStream);
        long validPassports = AdventOfCodeDay04.countValidPassports(passportBatchReader);
        assertEquals("validPassports", 230, validPassports);
    }

}
