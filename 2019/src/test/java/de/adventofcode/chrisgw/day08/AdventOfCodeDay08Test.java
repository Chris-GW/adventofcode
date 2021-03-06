package de.adventofcode.chrisgw.day08;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay08Test {

    @Test
    public void example01_part01() {
        int wide = 3;
        int height = 2;
        int[] imageData = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2 };
        int expectedSolution = 1;

        LayeredPixelImage layeredPixelImage = new LayeredPixelImage(wide, height, imageData);
        System.out.println(layeredPixelImage);
        AdventOfCodeDay08 aocDay08 = new AdventOfCodeDay08(layeredPixelImage);
        int solution = aocDay08.calculatePart01();
        assertEquals("solution part01", expectedSolution, solution);
    }


    @Test
    public void myPuzzleInput_part01() {
        String imageDataStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay08.txt");
        int wide = 25;
        int height = 6;
        int expectedSolution = 1474;

        LayeredPixelImage layeredPixelImage = LayeredPixelImage.parseLayeredPixelImageStr(wide, height, imageDataStr);
        AdventOfCodeDay08 aocDay08 = new AdventOfCodeDay08(layeredPixelImage);
        int solution = aocDay08.calculatePart01();
        assertEquals("solution part01", expectedSolution, solution);
    }


    // part 02

    @Test
    public void example02_part02() {
        int wide = 2;
        int height = 2;
        int[] imageData = new int[] { 0, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 2, 0, 0, 0, 0 };
        String expectedSolution = "" //
                + "█░\n"  //
                + "░█";

        LayeredPixelImage layeredPixelImage = new LayeredPixelImage(wide, height, imageData);
        AdventOfCodeDay08 aocDay08 = new AdventOfCodeDay08(layeredPixelImage);
        String solution = aocDay08.printVisiblePixelImagePart02();
        assertEquals("solution part02", expectedSolution, solution);
    }

    @Test
    public void myPuzzleInput_part02() {
        String imageDataStr = TestUtils.readSingleLineOfClassPathResource("/puzzleInputDay08.txt");
        int wide = 25;
        int height = 6;
        String expectedSolution = "" //
                + "██░░██░░██░░░███░░██░░░██\n" //
                + "███░█░██░█░██░█░██░█░██░█\n" //
                + "███░█░████░██░█░████░░░██\n" //
                + "███░█░████░░░██░████░██░█\n" //
                + "░██░█░██░█░█░██░██░█░██░█\n" //
                + "█░░███░░██░██░██░░██░░░██";

        LayeredPixelImage layeredPixelImage = LayeredPixelImage.parseLayeredPixelImageStr(wide, height, imageDataStr);
        AdventOfCodeDay08 aocDay08 = new AdventOfCodeDay08(layeredPixelImage);
        String solution = aocDay08.printVisiblePixelImagePart02();
        assertEquals("solution part01", expectedSolution, solution);
    }

}
