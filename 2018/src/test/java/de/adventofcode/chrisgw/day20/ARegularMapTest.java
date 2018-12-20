package de.adventofcode.chrisgw.day20;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;


public class ARegularMapTest {


    private final String roomRouteRegex_example01 = "^WNE$";
    private final String roomRouteRegex_example02 = "^ENWWW(NEEE|SSE(EE|N))$";
    private final String roomRouteRegex_example03 = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
    private final String roomRouteRegex_example04 = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
    private final String roomRouteRegex_example05 = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";


    @Test
    public void longestRouteLength_example01() {
        int expectedLongestRouteLength = 3;
        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example01);
        int longestRouteLength = facilityMap.longestRouteLength();
        assertEquals("longestRouteLength", expectedLongestRouteLength, longestRouteLength);
    }

    @Test
    public void longestRouteLength_example02() {
        int expectedLongestRouteLength = 10;
        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example02);
        int longestRouteLength = facilityMap.longestRouteLength();
        assertEquals("longestRouteLength", expectedLongestRouteLength, longestRouteLength);
    }

    @Test
    public void longestRouteLength_example03() {
        int expectedLongestRouteLength = 18;
        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example03);
        int longestRouteLength = facilityMap.longestRouteLength();
        assertEquals("longestRouteLength", expectedLongestRouteLength, longestRouteLength);
    }

    @Test
    public void longestRouteLength_example04() {
        int expectedLongestRouteLength = 23;
        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example04);
        int longestRouteLength = facilityMap.longestRouteLength();
        assertEquals("longestRouteLength", expectedLongestRouteLength, longestRouteLength);
    }

    @Test
    public void longestRouteLength_example05() {
        int expectedLongestRouteLength = 31;
        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example05);
        int longestRouteLength = facilityMap.longestRouteLength();
        assertEquals("longestRouteLength", expectedLongestRouteLength, longestRouteLength);
    }


    @Test
    public void longestRouteLength_myPuzzleInput() throws Exception {
        int expectedLongestRouteLength = 18;
        Path puzzleInputFile = TestUtils.getResourcePath("/day20/myPuzzleInput.txt");
        ARegularMap facilityMap = new ARegularMap(Files.lines(puzzleInputFile).findFirst().orElse(""));
        int longestRouteLength = facilityMap.longestRouteLength();
        assertEquals("longestRouteLength", expectedLongestRouteLength, longestRouteLength);
    }

}
