package de.adventofcode.chrisgw.day22;

import org.junit.Test;

import static org.junit.Assert.*;


public class AdventOfCodeDay22Test {


    @Test
    public void example01_part01() throws Exception {
        int depth = 510;
        int[] target = new int[] { 10, 10 };
        String expectCaveStr = "" //
                + "M=.|=.|.|=.\n" //
                + ".|=|=|||..|\n" //
                + ".==|....||=\n" //
                + "=.|....|.==\n" //
                + "=|..==...=.\n" //
                + "=||.=.=||=|\n" //
                + "|.=.===|||.\n" //
                + "|..==||=.|=\n" //
                + ".=..===..=|\n" //
                + ".======|||=\n" //
                + ".===|=|===T";
        int expectedRiskLevel = 114;

        AdventOfCodeDay22 aocDay22 = new AdventOfCodeDay22(depth, target);
        System.out.println(aocDay22);
        assertEquals("cave", expectCaveStr, aocDay22.toString());
        assertEquals("riskLevel", expectedRiskLevel, aocDay22.toalRiskLevel());

    }

    @Test
    public void myPuzzleInput_part01() throws Exception {
        int depth = 7863;
        int[] target = new int[] { 14, 760 };
        AdventOfCodeDay22 aocDay22 = new AdventOfCodeDay22(depth, target);
        System.out.println(aocDay22);

    }

}