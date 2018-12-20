package de.adventofcode.chrisgw.day20;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static de.adventofcode.chrisgw.day20.FacilityRouteDirection.*;
import static org.junit.Assert.*;


public class ARegularMapTest {


    private final String roomRouteRegex_example01 = "^WNE$";
    private final String roomRouteRegex_example02 = "^ENWWW(NEEE|SSE(EE|N))$";
    private final String roomRouteRegex_example03 = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
    private final String roomRouteRegex_example04 = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
    private final String roomRouteRegex_example05 = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";

    @Test
    public void example_01() {
        FacilityRoute expectedRootRoute = new FacilityRoute();
        expectedRootRoute.addDirection(WEST);
        expectedRootRoute.addDirection(NORTH);
        expectedRootRoute.addDirection(EAST);

        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example01);
        FacilityRoute rootRoute = facilityMap.getRootRoute();
        assertEquals("rootRoute", expectedRootRoute, rootRoute);

    }

    @Test
    public void example_02() {
        FacilityRoute expectedRootRoute = new FacilityRoute();
        expectedRootRoute.addDirections(EAST, NORTH, WEST, WEST, WEST);
        FacilityRoute firstBranchRoute = new FacilityRoute().addDirections(NORTH, EAST, EAST, EAST);
        expectedRootRoute.addBranchingRoutes(firstBranchRoute);

        FacilityRoute secondBranchRoute = new FacilityRoute().addDirections(SOUTH, SOUTH, EAST)
                .addBranchingRoutes(new FacilityRoute().addDirections(EAST, EAST),
                        new FacilityRoute().addDirections(NORTH));
        expectedRootRoute.addBranchingRoutes(secondBranchRoute);

        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example02);
        FacilityRoute rootRoute = facilityMap.getRootRoute();
        assertEquals("rootRoute", expectedRootRoute, rootRoute);
    }

    @Test
    public void example_03() {
        FacilityRoute expectedRootRoute = new FacilityRoute();
        expectedRootRoute.addDirections(EAST, NORTH, NORTH, WEST, SOUTH, WEST, WEST)
                .addBranchingRoutes(new FacilityRoute().addDirections(NORTH, EAST, WEST, SOUTH), new FacilityRoute())
                .addDirections(SOUTH, SOUTH, SOUTH, EAST, EAST, NORTH)
                .addBranchingRoutes(new FacilityRoute().addDirections(WEST, NORTH, SOUTH, EAST), new FacilityRoute())
                .addDirections(EAST, EAST)
                .addBranchingRoutes(new FacilityRoute().addDirections(SOUTH, WEST, EAST, NORTH), new FacilityRoute())
                .addDirections(NORTH, NORTH, NORTH);

        ARegularMap facilityMap = new ARegularMap(roomRouteRegex_example03);
        FacilityRoute rootRoute = facilityMap.getRootRoute();
        assertEquals("rootRoute", expectedRootRoute, rootRoute);
    }


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
